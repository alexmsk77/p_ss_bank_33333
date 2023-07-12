package com.bank.antifraud.repositories;

import com.bank.antifraud.entities.SuspiciousCardTransfer;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий класса SuspiciousCardTransfer.
 */
@Repository
public interface SuspiciousCardTransferRepository extends AbstractSuspiciousTransferRepository<SuspiciousCardTransfer, Integer> {

    /**
     * Метод для нахождения подозрительного трансфера с карты по cardTransferId.
     * @param id - принимает cardTransferId.
     * @return - возвращает найденный в БД подзрительный трансфер.
     */
    SuspiciousCardTransfer findByCardTransferId(Integer id);
}
