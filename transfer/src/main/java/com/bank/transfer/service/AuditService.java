package com.bank.transfer.service;

import com.bank.transfer.model.Audit;

public interface AuditService {

     void saveAuditLogging(Audit audit);
}
