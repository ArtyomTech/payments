package com.sfey.microservice.repository;

import com.sfey.microservice.repository.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> { }
