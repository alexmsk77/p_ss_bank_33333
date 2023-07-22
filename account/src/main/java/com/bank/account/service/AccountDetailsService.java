package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;


import java.util.List;

public interface AccountDetailsService {
    AccountDetailsDto findById(Long id);

    List<AccountDetailsDto> findAllById(List<Long> ids);

    AccountDetailsDto save(AccountDetailsDto accountDetails);

    AccountDetailsDto update(Long id, AccountDetailsDto accountDetails);
}
