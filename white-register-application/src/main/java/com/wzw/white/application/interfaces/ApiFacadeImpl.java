package com.wzw.white.application.interfaces;

import com.alibaba.fastjson.JSON;
import com.wzw.white.api.dto.ApiDTO;
import com.wzw.white.api.facade.ApiFacade;
import com.wzw.white.api.util.WrResult;
import com.wzw.white.api.util.WrResultBuilder;
import com.wzw.white.application.service.ApiQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/white")
public class ApiFacadeImpl implements ApiFacade {
    @Resource
    private ApiQueryService apiQueryService;

    @Override
    @RequestMapping("/register/queryOne")
    public WrResult<ApiDTO> queryOneMes(String key) {
        if (key.length() > 10 || key.length() <= 0) {
            return WrResultBuilder.builder("500", "入参异常", false, null).build();
        }
        ApiDTO apiDTO = apiQueryService.queryOneApi(key);
        log.info("com.wzw.white.application.interfaces.ApiFacadeImpl.queryOneMes={}", JSON.toJSONString(apiDTO));
        return WrResultBuilder.builder("200", null, true, apiDTO).build();
    }
}
