package com.wzw.white.infrastracture.exception;

import java.io.Serializable;

public class LogisticBizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -1;
    private String code;
    private String messgae;

    public String getMessgae() {
        return messgae;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    @Override
    public String toString() {
        return "LogisticException{" +
                "code='" + code + '\'' +
                ", messgae='" + messgae + '\'' +
                '}';
    }
}
