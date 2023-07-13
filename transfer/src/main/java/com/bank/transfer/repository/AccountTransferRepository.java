package com.bank.transfer.repository;

import com.bank.transfer.model.AccountTransfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTransferRepository extends TransferRepository<AccountTransfer, Long> {

    @Query("Select a from AccountTransfer a where a.account_number=:account_number")
    Optional<AccountTransfer>  findByAccountNumber(Long account_number);

}
