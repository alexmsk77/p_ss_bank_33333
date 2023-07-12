package com.bank.antifraud.repositories;

import com.bank.antifraud.entities.SuspiciousAccountTransfer;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий класса SuspiciousAccountTransfer.
 */
@Repository
public interface SuspiciousAccountTransferRepository extends AbstractSuspiciousTransferRepository<SuspiciousAccountTransfer, Integer> {

    /**
     * Метод для нахождения подозрительного трансфера с аккаунта по accountTransferId.
     * @param id - принимает accountTransferId.
     * @return - возвращает найденный в БД подзрительный трансфер.
     */
    SuspiciousAccountTransfer findByAccountTransferId(Integer id);
}
