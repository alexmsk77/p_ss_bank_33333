package com.bank.transfer.service;

import com.bank.transfer.model.Audit;
import com.bank.transfer.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditRepository auditRepository;

    @Override
    public void saveAuditLogging(Audit audit) {
        auditRepository.save(audit);
    }
}
