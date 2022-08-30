package com.wzw.white.infrastracture.exception;

public class BizException extends LogisticException{
    public BizException(BaseExcepMsg baseExcepMsg) {
        super(baseExcepMsg.getCode(), baseExcepMsg.getMsg());
    }
}
