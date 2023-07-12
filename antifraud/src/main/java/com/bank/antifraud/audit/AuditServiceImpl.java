package com.bank.antifraud.audit;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Имплементация сервиса аудита.
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository repository;

    @Override
    public void saveAudit(Audit audit) {
        repository.save(audit);
    }
}
