package com.bank.antifraud.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Entity
@Table(name = "suspicious_card_transfer", schema = "anti_fraud")
public class SuspiciousCardTransfer extends AbstractSuspiciousTransfer {

    @Column(name = "card_transfer_id", unique = true, nullable = false)
    @NotNull(message = "card_transfer_id cannot be null")
    private Integer cardTransferId;

}
