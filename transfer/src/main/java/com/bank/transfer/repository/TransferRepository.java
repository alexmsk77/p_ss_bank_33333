package com.bank.transfer.repository;

import com.bank.transfer.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface TransferRepository<T extends Transfer, ID extends Serializable> extends JpaRepository<T, ID> {

}
