package com.bank.antifraud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(description = "Сущность подозрительного трансфера с телефона")
@Getter
@Setter
public class SuspiciousPhoneTransferDTO extends AbstractSuspiciousTransferDTO {

    @Schema(description = "id трнасфера с телефона")
    @NotNull(message = "phone_transfer_id cannot be null")
    private Integer phoneTransferId;
}
