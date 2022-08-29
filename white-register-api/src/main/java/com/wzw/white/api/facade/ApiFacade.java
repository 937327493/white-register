package com.wzw.white.api.facade;

import com.wzw.white.api.dto.ApiDTO;
import com.wzw.white.api.util.WrResult;
import com.wzw.white.api.util.WrResultBuilder;

/**
 * 测试白名单接口
 */
public interface ApiFacade {
    WrResult<ApiDTO> queryOneMes(String key);
}
