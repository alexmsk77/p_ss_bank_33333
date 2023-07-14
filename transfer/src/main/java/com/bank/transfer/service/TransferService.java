package com.bank.transfer.service;

import com.bank.transfer.dto.TransferDTO;

import java.io.Serializable;
import java.util.List;

public interface TransferService<Transfer extends TransferDTO, ID extends Serializable> {

    public void makeTransaction(Transfer transfer);

    public Transfer getTransaction(ID id);

    public List<Transfer> getAllTransactions();

    public void deleteTransaction(ID id);

    public void updateTransaction(Transfer transfer);
}
