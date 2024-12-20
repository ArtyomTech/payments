package com.sfey.microservice.service.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentResult(
    Long senderAccountId,
    BigDecimal senderBalance,
    Long receiverAccountId,
    BigDecimal receiverBalance
) { }
