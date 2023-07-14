package com.bank.transfer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс - потомок от Transfer, необходимый для описания денежных транзакций по номеру счета.
 */
@Entity
@Getter
@Setter
@Table(name = "account_transfer", schema = "transfer")
public class AccountTransfer extends Transfer{

    @Column(name = "account_number", unique = true, nullable = false)
    @NotNull(message = "Field 'account_number' should be completed")
    private Long account_number;



}
