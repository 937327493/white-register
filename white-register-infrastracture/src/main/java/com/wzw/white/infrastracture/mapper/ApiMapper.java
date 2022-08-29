package com.wzw.white.infrastracture.mapper;

import com.wzw.white.infrastracture.dataobj.ApiDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiMapper {
    ApiDO queryOne(@Param("key") String key);
}
