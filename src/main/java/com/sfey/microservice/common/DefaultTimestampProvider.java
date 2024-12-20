package com.sfey.microservice.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DefaultTimestampProvider implements TimestampProvider {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }

}
