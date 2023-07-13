package com.bank.transfer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Класс - потомок от Transfer, необходимый для описания денежных транзакций по номеру телефона.
 */
@Entity
@Getter
@Setter
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer extends Transfer {

    @Column(name = "phone_number", unique = true, nullable = false)
    @NotNull(message = "Field 'phone_number' should be completed")
    private Long phone_number;
}
