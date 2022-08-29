package com.wzw.white.infrastracture.converter;

import com.wzw.white.api.dto.ApiDTO;
import com.wzw.white.infrastracture.dataobj.ApiDO;

public class ApiConverter {
    public static ApiDTO doConverterDto(ApiDO apiDO) {
        if (apiDO == null) return null;
        ApiDTO build = ApiDTO.builder()
                .key(apiDO.getKeyMsg())
                .value(apiDO.getValue())
                .build();
        return build;
    }
}
