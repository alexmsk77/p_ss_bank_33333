package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.exceptions.NumberAlreadyExistsException;
import com.bank.transfer.exceptions.TransactionNotFoundException;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.model.AccountTransfer;
import com.bank.transfer.repository.AccountTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, в котором содержится основная бизнес-логика CRUD оперций для сущности AccountTransfer.
 */
@Service
@RequiredArgsConstructor
public class AccountTransferService implements TransferService<AccountTransferDTO, Long> {

    private final AccountTransferRepository accountTransferRepository;
    private final AccountTransferMapper accountTransferMapper;

    /**
     * Метод, отвечающий за сохранения денежной транзакции в БД по номеру счета.
     * @param accountTransferDTO - входящий денежный перевод по номеру счета
     */
    @Override
    @Transactional
    public void makeTransaction(AccountTransferDTO accountTransferDTO) {
        Optional optionalAccount = accountTransferRepository
                .findByAccountNumber(accountTransferDTO.getAccount_number());
        if (optionalAccount.isPresent()) {
            throw new NumberAlreadyExistsException("This account number already exists!");
        }
        AccountTransfer accountTransfer = accountTransferMapper.accountDtoToAccount(accountTransferDTO);
        accountTransferRepository.save(accountTransfer);
        accountTransferDTO.setId(accountTransfer.getId());
    }

    /**
     * Получение информации о денежной транзакции по номеру счета.
     * @param id - номер транзакции
     * @return
     */
    @Override
    public AccountTransferDTO getTransaction(Long id) {
        Optional<AccountTransfer> optionalAccountTransfer = accountTransferRepository.findById(id);
        return accountTransferMapper.accountToDto(optionalAccountTransfer
                        .orElseThrow(() -> new TransactionNotFoundException("Transaction doesn't exist!")));
    }

    /**
     * Получение информации о всех денежных транзакциях по номеру счета.
     * @return
     */
    @Override
    public List<AccountTransferDTO> getAllTransactions() {
        List<AccountTransfer> accountTransferList = accountTransferRepository.findAll();
        return accountTransferList.stream()
                .map((accountTransfer) -> accountTransferMapper.accountToDto(accountTransfer))
                .collect(Collectors.toList());
    }

    /**
     * Удаление транзакции по номеру счета
     * @param id - номер транзакции
     */
    @Transactional
    @Override
    public void deleteTransaction(Long id) {
        Optional<AccountTransfer> optionalAccountTransfer = accountTransferRepository.findById(id);
        if (optionalAccountTransfer.isEmpty()) {
            throw new NumberAlreadyExistsException("Transaction doesn't exist!");
        }
        accountTransferRepository.deleteById(id);
    }

    /**
     * Изменение денежной транзакции по номеру счета, учитывая наличие уникальных полей
     * @param accountTransferDTO - объект, который содержит изменённую информацию
     */
    @Transactional
    @Override
    public void updateTransaction(AccountTransferDTO accountTransferDTO) {

        Optional<AccountTransfer> account = accountTransferRepository
                .findByAccountNumber(accountTransferDTO.getAccount_number());
        AccountTransfer existingAccountTransfer = accountTransferRepository
                .findById(accountTransferDTO.getId()).get();

        if (account.isEmpty()) {

            existingAccountTransfer.setAccount_number(accountTransferDTO.getAccount_number());

        } else if (account.get().getId() != accountTransferDTO.getId()) {

            throw new NumberAlreadyExistsException("This account number already exists!");
        }
        existingAccountTransfer =
                updateUnUniqueFields(existingAccountTransfer,
                        accountTransferMapper.accountDtoToAccount(accountTransferDTO));
        accountTransferRepository.save(existingAccountTransfer);

    }

    private AccountTransfer updateUnUniqueFields(AccountTransfer existingAccount, AccountTransfer updatedAccount) {
        existingAccount.setAmount(updatedAccount.getAmount());
        existingAccount.setPurpose(updatedAccount.getPurpose());
        existingAccount.setAccount_details_id(updatedAccount.getAccount_details_id());
        return existingAccount;
    }


}
