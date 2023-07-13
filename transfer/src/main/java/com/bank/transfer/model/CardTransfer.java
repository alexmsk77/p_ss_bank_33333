package com.bank.transfer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс - потомок от Transfer, необходимый для описания денежных транзакций по номеру карты.
 */
@Entity
@Getter
@Setter
@Table(name = "card_transfer", schema = "transfer")
public class CardTransfer extends Transfer{

    @Column(name = "card_number", unique = true, nullable = false)
    @NotNull(message = "Field 'card_number' should be completed")
    private Long card_number;

}
