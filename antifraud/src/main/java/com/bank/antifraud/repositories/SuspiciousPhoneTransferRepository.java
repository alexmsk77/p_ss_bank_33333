package com.bank.antifraud.repositories;

import com.bank.antifraud.entities.SuspiciousPhoneTransfer;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий класса SuspiciousPhoneTransfer.
 */
@Repository
public interface SuspiciousPhoneTransferRepository extends AbstractSuspiciousTransferRepository<SuspiciousPhoneTransfer, Integer> {

    /**
     * Метод для нахождения подозрительного трансфера с телефона по phoneTransferId.
     * @param id - принимает phoneTransferId.
     * @return - возвращает найденный в БД подзрительный трансфер.
     */
    SuspiciousPhoneTransfer findByPhoneTransferId(Integer id);
}
