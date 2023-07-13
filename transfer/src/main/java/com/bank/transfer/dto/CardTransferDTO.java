package com.bank.transfer.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class CardTransferDTO  extends TransferDTO{

    @NotNull(message = "This field should be completed")
    private Long card_number;

}
