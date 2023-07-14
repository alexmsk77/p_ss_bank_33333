package com.bank.transfer.dto;


import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * Родительский класс, предназначенный для описания объектов передачи данных конечному пользователю.
 */
@Data
public class TransferDTO {

    private Long id;

    @Digits(integer = 20, fraction = 2, message = "Amount should be in this format: xxxx.xx")
    @Positive(message = "Amount should be positive")
    private BigDecimal amount;

    private String purpose;

    @NotNull(message = "Field 'account_details_id' should be completed")
    private Long account_details_id;
}
