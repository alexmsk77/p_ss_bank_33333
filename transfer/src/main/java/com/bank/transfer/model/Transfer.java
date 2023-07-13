package com.bank.transfer.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * Родительский класс, который необходим для описания основных сущностей.
 */
@MappedSuperclass
@Data
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", precision = 20, scale = 2, nullable = false)
    @NotNull(message = "Field 'amount' should be completed")
    @Positive(message = "Amount should be positive")
    private BigDecimal amount;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "account_details_id", nullable = false)
    @NotNull(message = "Field 'account_details_id' should be completed")
    private Long account_details_id;
}
