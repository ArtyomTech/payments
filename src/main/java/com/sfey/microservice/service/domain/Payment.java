package com.sfey.microservice.service.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public record Payment(
    @NotNull Long senderAccountId,
    @NotNull Long receiverAccountId,

    @NotNull
    @Digits(integer = 18, fraction = 2)
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    BigDecimal amount
) { }
