package com.bank.transfer.repository;

import com.bank.transfer.model.PhoneTransfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhoneTransferRepository extends TransferRepository<PhoneTransfer, Long> {

    @Query("Select a from PhoneTransfer a where a.phone_number=:phone_number")
    Optional<PhoneTransfer> findByPhoneNumber(Long phone_number);
}
