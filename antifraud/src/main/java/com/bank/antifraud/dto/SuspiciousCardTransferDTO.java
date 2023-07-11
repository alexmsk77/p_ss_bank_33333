package com.bank.antifraud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Schema(description = "Сущность подозрительного трансфера с карты")
@Getter
@Setter
public class SuspiciousCardTransferDTO extends AbstractSuspiciousTransferDTO{

    @Schema(description = "id трнасфера с карты")
    @NotNull(message = "card_transfer_id cannot be null")
    private Integer cardTransferId;
}
