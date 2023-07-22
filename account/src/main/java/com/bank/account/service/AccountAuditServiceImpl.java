package com.bank.account.service;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;
import com.bank.account.exception.ExceptionReturner;
import com.bank.account.mapper.AccountAuditMapper;
import com.bank.account.repository.AccountAuditRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountAuditServiceImpl implements AccountAuditService {
    AccountAuditRepository repository;
    AccountAuditMapper mapper;
    ExceptionReturner exceptionReturner;
    @Override
    public AuditDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(
                        () -> exceptionReturner.getEntityNotFoundException("Не существующий id" + id)
                )
        );
    }
}
