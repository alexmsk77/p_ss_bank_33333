package com.bank.transfer.service;

import com.bank.transfer.model.Audit;

public interface AuditService {

    public void saveAuditLogging(Audit audit);
}
