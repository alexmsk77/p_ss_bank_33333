package com.bank.account.service;

import com.bank.account.dto.AuditDto;


public interface AccountAuditService {
    AuditDto findById(Long id);
}
