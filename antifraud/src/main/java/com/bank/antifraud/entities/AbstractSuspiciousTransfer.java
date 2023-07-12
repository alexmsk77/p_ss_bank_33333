package com.bank.antifraud.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Класс, который нужен только для наследования всеми тремя сущностями.
 */
@MappedSuperclass
@Data
public class AbstractSuspiciousTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_blocked", nullable = false)
    @NotNull(message = "is_blocked cannot be null")
    private Boolean isBlocked;

    @Column(name = "is_suspicious", nullable = false)
    @NotNull(message = "is_suspicious cannot be null")
    private Boolean isSuspicious;

    @Column(name = "blocked_reason")
    private String blockedReason;

    @Column(name = "suspicious_reason", nullable = false)
    @NotNull(message = "suspicious_reason cannot be null")
    private String suspiciousReason;
}
