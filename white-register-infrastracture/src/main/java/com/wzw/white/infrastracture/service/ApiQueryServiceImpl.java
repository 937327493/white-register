package com.wzw.white.infrastracture.service;

import com.alibaba.fastjson.JSON;
import com.wzw.white.api.dto.ApiDTO;
import com.wzw.white.application.service.ApiQueryService;
import com.wzw.white.infrastracture.converter.ApiConverter;
import com.wzw.white.infrastracture.dataobj.ApiDO;
import com.wzw.white.infrastracture.event.CommonEventPublisher;
import com.wzw.white.infrastracture.event.EventA;
import com.wzw.white.infrastracture.exception.BizException;
import com.wzw.white.infrastracture.exception.ExceptionEnum;
import com.wzw.white.infrastracture.mapper.ApiMapper;
import com.wzw.white.infrastracture.redis.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ApiQueryServiceImpl implements ApiQueryService {
    @Resource
    private ApiMapper apiMapper;
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private CommonEventPublisher commonEventPublisher;
    @Override
    public ApiDTO queryOneApi(String key) {
        //手写分布式锁
        String lockKey = UUID.randomUUID().toString().substring(0, 10);
        commonEventPublisher.publishEvent(new EventA("A事件"));
        try {
            //查缓存
            String apiString = redisTemplate.opsForValue().get(key);
            if (apiString != null) {
                ApiDO apiDO1 = JSON.parseObject(apiString, ApiDO.class);
                return ApiConverter.doConverterDto(apiDO1);
            }
            log.warn("com.wzw.white.infrastracture.service.ApiQueryServiceImpl.queryOneApi缓存失效={}", key);
            //加分布式锁
            while (!redisTemplate.opsForValue().setIfAbsent("apiOneLock", lockKey, Duration.ofSeconds(15))) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //查白名单得到偏移量
            Object apiOneWhiteRegister = redisTemplate.opsForHash().get("apiOneWhiteRegister", key);
            if (apiOneWhiteRegister != null) {
                Long aLong = Long.valueOf((int) apiOneWhiteRegister);
                Boolean apiOneWhiteRegisterBit = redisTemplate.opsForValue().getBit("apiOneWhiteRegisterBit", aLong);
                if (!apiOneWhiteRegisterBit) throw new BizException(ExceptionEnum.DATABASENOTFINDTHISMSG);
            }

            ApiDO apiDO = apiMapper.queryOne(key);
            long l = RedisConfig.getSequenceBit();
            //加入白名单和bitmaps
            redisTemplate.opsForHash().put("apiOneWhiteRegister", key, l);
            if (apiDO == null) {
                redisTemplate.opsForValue().setBit("apiOneWhiteRegisterBit", l, false);
                throw new BizException(ExceptionEnum.DATABASENOTFINDTHISMSG);
            }
            redisTemplate.opsForValue().setBit("apiOneWhiteRegisterBit", l, true);
            redisTemplate.opsForValue().set(key, JSON.toJSONString(apiDO));
            ApiDTO apiDTO = ApiConverter.doConverterDto(apiDO);
            return apiDTO;
        } catch (Exception e) {
            log.error("com.wzw.white.infrastracture.service.ApiQueryServiceImpl.queryOneApi查询失败={}", key, e);
        } finally {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // 使用redis执行lua执行
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            // 设置一下返回值类型 为Long
            // 因为删除判断的时候，返回的0,给其封装为数据类型。如果不封装那么默认返回String 类型，
            // 那么返回字符串与0 会有发生错误。
            redisScript.setResultType(Long.class);
            // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
            redisTemplate.execute(redisScript, Arrays.asList(key), lockKey);
            return null;
        }
    }
}
