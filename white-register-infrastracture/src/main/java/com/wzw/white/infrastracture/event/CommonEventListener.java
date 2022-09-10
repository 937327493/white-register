package com.wzw.white.infrastracture.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 事件监听者
 */
@Service
public class CommonEventListener {
    @EventListener
    @Async
    public void handleEventA(EventA eventA) {
        System.out.println(eventA.getEventName());
    }
}
