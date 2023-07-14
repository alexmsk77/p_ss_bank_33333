package com.bank.transfer.repository;

import com.bank.transfer.model.CardTransfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardTransferRepository extends TransferRepository<CardTransfer, Long> {

    @Query("Select a from CardTransfer a where a.card_number=:card_number")
    Optional<CardTransfer> findByCardNumber(Long card_number);
}
