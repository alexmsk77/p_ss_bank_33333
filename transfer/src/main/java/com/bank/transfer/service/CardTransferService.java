package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.exceptions.NumberAlreadyExistsException;
import com.bank.transfer.exceptions.TransactionNotFoundException;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.model.CardTransfer;
import com.bank.transfer.repository.CardTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс, в котором содержится основная бизнес-логика CRUD оперций для сущности CardTransfer.
 */
@Service
@RequiredArgsConstructor
public class CardTransferService implements TransferService<CardTransferDTO, Long>{

    private final CardTransferRepository cardTransferRepository;
    private final CardTransferMapper cardTransferMapper;

    /**
     * Метод, отвечающий за сохранения денежной транзакции в БД по номеру карты.
     * @param cardTransferDTO - входящий денежный перевод по номеру карты
     */
    @Override
    @Transactional
    public void makeTransaction(CardTransferDTO cardTransferDTO) {
        Optional optionalCard = cardTransferRepository
                .findByCardNumber(cardTransferDTO.getCard_number());
        if (optionalCard.isPresent()) {
            throw new NumberAlreadyExistsException("This card number already exists!");
        }
        CardTransfer cardTransfer = cardTransferMapper.cardDtoToCard(cardTransferDTO);
        cardTransferRepository.save(cardTransfer);
        cardTransferDTO.setId(cardTransfer.getId());
    }

    /**
     * Получение информации о денежной транзакции по номеру карты.
     * @param id - номер транзакции
     * @return
     */
    @Override
    public CardTransferDTO getTransaction(Long id) {
        Optional<CardTransfer> optionalCardTransfer = cardTransferRepository.findById(id);
        return cardTransferMapper.cardToDto(optionalCardTransfer
                .orElseThrow(() -> new TransactionNotFoundException("Transaction doesn't exist!")));
    }


    /**
     * Получение информации о всех денежных транзакциях по номеру карты.
     * @return
     */
    @Override
    public List<CardTransferDTO> getAllTransactions() {
        List<CardTransfer> cardTransferList = cardTransferRepository.findAll();
        return cardTransferList.stream()
                .map((cardTransfer) -> cardTransferMapper.cardToDto(cardTransfer))
                .collect(Collectors.toList());
    }

    /**
     * Удаление транзакции по номеру карты
     * @param id - номер транзакции
     */
    @Transactional
    @Override
    public void deleteTransaction(Long id) {
        Optional<CardTransfer> optionalCardTransfer = cardTransferRepository.findById(id);
        if (optionalCardTransfer.isEmpty()) {
            throw new NumberAlreadyExistsException("Transaction doesn't exist!");
        }
        cardTransferRepository.deleteById(id);
    }

    /**
     * Изменение денежной транзакции по номеру карты, учитывая наличие уникальных полей
     * @param cardTransferDTO - объект, который содержит изменённую информацию
     */
    @Transactional
    @Override
    public void updateTransaction(CardTransferDTO cardTransferDTO) {

        Optional<CardTransfer> card = cardTransferRepository
                .findByCardNumber(cardTransferDTO.getCard_number());
        CardTransfer existingCardTransfer = cardTransferRepository
                .findById(cardTransferDTO.getId()).get();

        if (card.isEmpty()) {

            existingCardTransfer.setCard_number(cardTransferDTO.getCard_number());

        } else if (card.get().getId() != cardTransferDTO.getId()) {

            throw new NumberAlreadyExistsException("This card number already exists!");
        }
        existingCardTransfer =
                updateUnUniqueFields(existingCardTransfer,
                        cardTransferMapper.cardDtoToCard(cardTransferDTO));
        cardTransferRepository.save(existingCardTransfer);

    }

    private CardTransfer updateUnUniqueFields(CardTransfer existingCardTransfer, CardTransfer updatedCardTransfer) {
        existingCardTransfer.setAmount(updatedCardTransfer.getAmount());
        existingCardTransfer.setPurpose(updatedCardTransfer.getPurpose());
        existingCardTransfer.setAccount_details_id(updatedCardTransfer.getAccount_details_id());
        return existingCardTransfer;
    }
}
