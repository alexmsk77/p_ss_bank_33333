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
@Table(name = "suspicious_phone_transfers", schema = "anti_fraud")
public class SuspiciousPhoneTransfer extends AbstractSuspiciousTransfer {

    @Column(name = "phone_transfer_id", unique = true, nullable = false)
    @NotNull(message = "phone_transfer_id cannot be null")
    private Integer phoneTransferId;

}
