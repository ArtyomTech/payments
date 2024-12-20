package com.sfey.microservice.repository;

import com.sfey.microservice.repository.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> { }
