package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.exceptions.NumberAlreadyExistsException;
import com.bank.transfer.exceptions.TransactionNotFoundException;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.model.PhoneTransfer;
import com.bank.transfer.repository.PhoneTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, в котором содержится основная бизнес-логика CRUD оперций для сущности PhoneTransfer.
 */
@Service
@RequiredArgsConstructor
public class PhoneTransferService implements TransferService<PhoneTransferDTO, Long> {

    private final PhoneTransferRepository phoneTransferRepository;
    private final PhoneTransferMapper phoneTransferMapper;

    /**
     * Метод, отвечающий за сохранения денежной транзакции в БД по номеру телефона.
     * @param phoneTransferDTO - входящий денежный перевод по номеру карты
     */
    @Override
    @Transactional
    public void makeTransaction(PhoneTransferDTO phoneTransferDTO) {
        Optional optionalPhone = phoneTransferRepository
                .findByPhoneNumber(phoneTransferDTO.getPhone_number());
        if (optionalPhone.isPresent()) {
            throw new NumberAlreadyExistsException("This phone number already exists!");
        }
        PhoneTransfer phoneTransfer = phoneTransferMapper.phoneDtoToPhone(phoneTransferDTO);
        phoneTransferRepository.save(phoneTransfer);
        phoneTransferDTO.setId(phoneTransfer.getId());
    }

    /**
     * Получение информации о денежной транзакции по номеру телефона.
     * @param id - номер транзакции
     * @return
     */
    @Override
    public PhoneTransferDTO getTransaction(Long id) {
        Optional<PhoneTransfer> optionalPhoneTransfer = phoneTransferRepository.findById(id);
        return phoneTransferMapper.phoneToDto(optionalPhoneTransfer
                .orElseThrow(() -> new TransactionNotFoundException("Transaction doesn't exist!")));
    }

    /**
     * Получение информации о всех денежных транзакциях по номеру телефона.
     * @return
     */
    @Override
    public List<PhoneTransferDTO> getAllTransactions() {
        List<PhoneTransfer> phoneTransferList = phoneTransferRepository.findAll();
        return phoneTransferList.stream()
                .map((phoneTransfer) -> phoneTransferMapper.phoneToDto(phoneTransfer))
                .collect(Collectors.toList());
    }

    /**
     * Удаление транзакции по номеру телефона
     * @param id - номер транзакции
     */
    @Transactional
    @Override
    public void deleteTransaction(Long id) {
        Optional<PhoneTransfer> optionalPhoneTransfer = phoneTransferRepository.findById(id);
        if (optionalPhoneTransfer.isEmpty()) {
            throw new NumberAlreadyExistsException("Transaction doesn't exist!");
        }
        phoneTransferRepository.deleteById(id);
    }

    /**
     * Изменение денежной транзакции по номеру телефона, учитывая наличие уникальных полей
     * @param phoneTransferDTO - объект, который содержит изменённую информацию
     */
    @Transactional
    @Override
    public void updateTransaction(PhoneTransferDTO phoneTransferDTO) {

        Optional<PhoneTransfer> phone = phoneTransferRepository
                .findByPhoneNumber(phoneTransferDTO.getPhone_number());
        PhoneTransfer existingPhoneTransfer = phoneTransferRepository
                .findById(phoneTransferDTO.getId()).get();

        if (phone.isEmpty()) {

            existingPhoneTransfer.setPhone_number(phoneTransferDTO.getPhone_number());

        } else if (phone.get().getId() != phoneTransferDTO.getId()) {

            throw new NumberAlreadyExistsException("This phone number already exists!");
        }
        existingPhoneTransfer =
                updateUnUniqueFields(existingPhoneTransfer,
                        phoneTransferMapper.phoneDtoToPhone(phoneTransferDTO));
        phoneTransferRepository.save(existingPhoneTransfer);

    }

    private PhoneTransfer updateUnUniqueFields(PhoneTransfer existingPhoneTransfer, PhoneTransfer updatedPhoneTransfer) {
        existingPhoneTransfer.setAmount(updatedPhoneTransfer.getAmount());
        existingPhoneTransfer.setPurpose(updatedPhoneTransfer.getPurpose());
        existingPhoneTransfer.setAccount_details_id(updatedPhoneTransfer.getAccount_details_id());
        return existingPhoneTransfer;
    }
}
