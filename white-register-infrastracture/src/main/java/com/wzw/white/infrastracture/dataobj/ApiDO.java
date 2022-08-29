package com.wzw.white.infrastracture.dataobj;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ApiDO {
    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String status;
    private String keyMsg;
    private String value;
    private Long version;
}
