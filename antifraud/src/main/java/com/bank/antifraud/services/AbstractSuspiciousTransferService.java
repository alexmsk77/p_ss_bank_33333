package com.bank.antifraud.services;

import com.bank.antifraud.audit.AuditAspect;
import com.bank.antifraud.dto.AbstractSuspiciousTransferDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Общий для всех классов подозрительных трансферов сервисный интерфейс.
 * @param <Transfer> - дженерик для класса подозрительных трансферов.
 * @param <ID> - дженерик для ID сущностей подозрительных трансферов.
 */
public interface AbstractSuspiciousTransferService<Transfer extends AbstractSuspiciousTransferDTO, ID extends Serializable> {

    Logger logger = LoggerFactory.getLogger(AuditAspect.class.getName());

    /**
     * Добавление подозрительного трансфера в БД.
     * @param transfer - принимает подозрительный трансфер.
     */
    void saveSuspiciousTransfer(Transfer transfer);

    /**
     * Удаление подозрительного трансфера из БД по id.
     * @param id - принимает id трансфера.
     */
    void deleteSuspiciousTransferById(ID id);

    /**
     * Нахождение подозрительного трансфера по id.
     * @param id - принимает id трансфера.
     */
    Transfer findSuspiciousTransferById(ID id);

    /**
     * Нахождение всех подозрительныз трансферов.
     */
    List<Transfer> findAllSuspiciousTransfers();

    /**
     * Обновление сущетсвующего в БД подозрительного трансфера по его id.
     * @param id - принимает id трансфера.
     * @param transfer - принимает подозрительный трансфер с обновленными полями.
     */
    void updateSuspiciousTransferById(ID id, Transfer transfer);
}
