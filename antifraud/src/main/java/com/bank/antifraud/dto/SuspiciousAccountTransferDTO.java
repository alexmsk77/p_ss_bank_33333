package com.bank.antifraud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(description = "Сущность подозрительного трансфера с аккаунта")
@Getter
@Setter
public class SuspiciousAccountTransferDTO extends AbstractSuspiciousTransferDTO {

    @Schema(description = "id трнасфера с аккаунта")
    @NotNull(message = "account_transfer_id cannot be null")
    private Integer accountTransferId;


}
