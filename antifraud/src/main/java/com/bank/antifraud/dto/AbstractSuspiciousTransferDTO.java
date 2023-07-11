package com.bank.antifraud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ДТО для наследования тремя ДТО, с которыми работает приложение.
 */
@Data
public class AbstractSuspiciousTransferDTO {

    @Schema(description = "Заблокирован ли трансфер")
    @NotNull(message = "is_blocked cannot be null")
    private Boolean isBlocked;

    @Schema(description = "Подозрителен ли трансфер")
    @NotNull(message = "is_suspicious cannot be null")
    private Boolean isSuspicious;

    @Schema(description = "причина блокировки")
    private String blockedReason;

    @Schema(description = "причина подозрительности")
    @NotNull(message = "suspicious_reason cannot be null")
    private String suspiciousReason;
}
