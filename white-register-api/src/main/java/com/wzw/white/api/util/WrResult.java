package com.wzw.white.api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WrResult<T> {
    private String code;
    private String msg;
    private Boolean success;
    private T data;
}
