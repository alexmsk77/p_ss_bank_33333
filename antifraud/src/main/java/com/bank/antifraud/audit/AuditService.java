package com.bank.antifraud.audit;


/**
 * Сервис аудита.
 */
public interface AuditService {

    void saveAudit(Audit audit);
}
