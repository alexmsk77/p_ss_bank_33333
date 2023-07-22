package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.exception.ExceptionReturner;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private static final String MESSAGE_PREFIX = "Не существующий id";
    private final AccountDetailsMapper mapper;
    private final AccountDetailsRepository repository;

    private final ExceptionReturner exceptionReturner;

    @Override
    public AccountDetailsDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id))
        );
    }

    @Override
    public List<AccountDetailsDto> findAllById(List<Long> ids) {

        final List<AccountDetailsEntity> accountDetailsList = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id)))
                .toList();
        return mapper.toDtoList(accountDetailsList);
    }

    @Override
    @Transactional
    public AccountDetailsDto save(AccountDetailsDto accountDetailsDto) {

        final AccountDetailsEntity accountDetails = repository.save(
                mapper.toEntity(accountDetailsDto)
        );

        return mapper.toDto(accountDetails);
    }

    @Override
    @Transactional
    public AccountDetailsDto update(Long id, AccountDetailsDto accountDetailsDto) {

        final AccountDetailsEntity accountDetails = repository.findById(id)
                .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id));

        final AccountDetailsEntity updateAccountDetails = repository.save(
                mapper.mergeToEntity(accountDetails, accountDetailsDto)
        );

        return mapper.toDto(updateAccountDetails);
    }
}

