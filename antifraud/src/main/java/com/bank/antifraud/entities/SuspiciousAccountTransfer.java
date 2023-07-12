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
@Table(name = "suspicious_account_transfers", schema = "anti_fraud")
public class SuspiciousAccountTransfer extends AbstractSuspiciousTransfer {

    @Column(name = "account_transfer_id", unique = true, nullable = false)
    @NotNull(message = "account_transfer_id cannot be null")
    private Integer accountTransferId;

    public void setAccountTransferId(Integer accountTransferId) {
        this.accountTransferId = accountTransferId;
    }

    public Integer getAccountTransferId() {
        return accountTransferId;
    }
}
