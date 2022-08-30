package com.wzw.white.infrastracture.exception;


public class LogisticException extends LogisticBizException{
    LogisticException(String code, String messgae) {
        setCode(code);
        setMessgae(messgae);
    }

}
