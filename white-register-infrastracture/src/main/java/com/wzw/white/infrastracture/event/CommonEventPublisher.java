package com.wzw.white.infrastracture.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class CommonEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;
    public CommonEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    public void publishEvent(EventA eventA) {
        applicationEventPublisher.publishEvent(eventA);
    }
}
