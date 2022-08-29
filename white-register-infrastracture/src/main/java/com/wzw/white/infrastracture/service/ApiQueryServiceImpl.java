package com.wzw.white.infrastracture.service;

import com.wzw.white.api.dto.ApiDTO;
import com.wzw.white.application.service.ApiQueryService;
import com.wzw.white.infrastracture.converter.ApiConverter;
import com.wzw.white.infrastracture.dataobj.ApiDO;
import com.wzw.white.infrastracture.mapper.ApiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApiQueryServiceImpl implements ApiQueryService {
    @Resource
    private ApiMapper apiMapper;
    @Override
    public ApiDTO queryOneApi(String key) {
        ApiDO apiDO = apiMapper.queryOne(key);
        ApiDTO apiDTO = ApiConverter.doConverterDto(apiDO);
        return apiDTO;
    }
}
