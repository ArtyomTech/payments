package com.sfey.microservice.service;

import com.sfey.microservice.common.TimestampProvider;
import com.sfey.microservice.exception.AccountNotFoundException;
import com.sfey.microservice.exception.InsufficientBalanceException;
import com.sfey.microservice.service.domain.Payment;
import com.sfey.microservice.service.domain.PaymentResult;
import com.sfey.microservice.repository.AccountRepository;
import com.sfey.microservice.repository.PaymentRepository;
import com.sfey.microservice.repository.entity.AccountEntity;
import com.sfey.microservice.repository.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    private final TimestampProvider timestampProvider;

    @Transactional
    public PaymentResult processPayment(Payment payment) {
        log.info(
            "Processing payment from sender {} to receiver {}",
            payment.senderAccountId(),
            payment.receiverAccountId()
        );

        AccountEntity sender = accountRepository.findById(payment.senderAccountId())
            .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));
        AccountEntity receiver = accountRepository.findById(payment.receiverAccountId())
            .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        BigDecimal amount = payment.amount();
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance in sender's account");
        }

        savePayment(sender, receiver);
        return updateAccounts(sender, amount, receiver);
    }

    private void savePayment(AccountEntity sender, AccountEntity receiver) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
            .senderAccount(sender)
            .receiverAccount(receiver)
            .timestamp(timestampProvider.now())
            .build();
        paymentRepository.save(paymentEntity);
    }

    private PaymentResult updateAccounts(AccountEntity sender, BigDecimal amount, AccountEntity receiver) {
        sender.setBalance(sender.getBalance().subtract(amount));
        AccountEntity updatedSender = accountRepository.save(sender);

        receiver.setBalance(receiver.getBalance().add(amount));
        AccountEntity updatedReceiver = accountRepository.save(receiver);

        return PaymentResult.builder()
            .senderAccountId(updatedSender.getId())
            .senderBalance(updatedSender.getBalance())
            .receiverAccountId(updatedReceiver.getId())
            .receiverBalance(updatedReceiver.getBalance())
            .build();
    }

}
