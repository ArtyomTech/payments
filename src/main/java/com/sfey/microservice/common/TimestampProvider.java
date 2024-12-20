package com.sfey.microservice.common;

import java.time.LocalDateTime;

public interface TimestampProvider {

    LocalDateTime now();

}
