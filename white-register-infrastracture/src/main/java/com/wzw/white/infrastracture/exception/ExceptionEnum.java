package com.wzw.white.infrastracture.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionEnum implements BaseExcepMsg{

    DATABASENOTFINDTHISMSG("DATABASENOTFINDTHISMSG", "数据库没有该信息");

    private String code;
    private String msg;


    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
