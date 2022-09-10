package com.wzw.white.infrastracture.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventA {
    private String eventName;
    public EventA(String eventName) {
        this.eventName = eventName;
    }
}
