package com.wzw.white.api.util;

import lombok.*;

/**
 * 返回值容器
 */
public class WrResultBuilder {
    private WrResult wrResult;

    public static <T> WrResultBuilder builder(String code, String msg, Boolean success, T data) {
        WrResultBuilder objectWrResultBuilder = new WrResultBuilder();
        WrResult<T> wrResult = new WrResult();
        wrResult.setCode(code);
        wrResult.setMsg(msg);
        wrResult.setSuccess(success);
        wrResult.setData(data);
        objectWrResultBuilder.wrResult = wrResult;
        return objectWrResultBuilder;
    }

    public WrResult build() {
        return this.wrResult;
    }


}
