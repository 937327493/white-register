package com.wzw.white.api.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class ApiDTO implements Serializable {
    private static final Long serialVersionUID = -1L;

    private String key;
    private String value;
}
