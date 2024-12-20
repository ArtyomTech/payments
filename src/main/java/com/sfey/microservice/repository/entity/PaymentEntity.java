package com.sfey.microservice.repository.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Entity
@Builder
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false)
    private AccountEntity senderAccount;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id", nullable = false)
    private AccountEntity receiverAccount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

}
