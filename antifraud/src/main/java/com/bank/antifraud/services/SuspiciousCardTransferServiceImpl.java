package com.bank.antifraud.services;


import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entities.SuspiciousCardTransfer;
import com.bank.antifraud.exception.SuspiciousTransferNotFoundException;
import com.bank.antifraud.exception.UniqueFieldAlreadyExistsException;
import com.bank.antifraud.mappers.SuspiciousCardTransferMapper;
import com.bank.antifraud.repositories.SuspiciousCardTransferRepository;
import com.bank.antifraud.validator.SuspiciousTransferValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Имплементация сервиса для класса SuspiciousCardTransfer.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SuspiciousCardTransferServiceImpl implements AbstractSuspiciousTransferService<SuspiciousCardTransferDTO, Integer> {

    private final SuspiciousCardTransferRepository cardRepo;
    private final SuspiciousCardTransferMapper mapper;
    private final SuspiciousTransferValidator<SuspiciousCardTransfer> validator;

    @Override
    public void saveSuspiciousTransfer(SuspiciousCardTransferDTO dto) {
        if (cardRepo.findByCardTransferId(dto.getCardTransferId()) != null) {
            String message = String.format("Suspicious card transfer with cardTransferId=%d already exists", dto.getCardTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        validator.validateTransfer(mapper.toEntity(dto));
        cardRepo.save(mapper.toEntity(dto));
    }


    @Override
    public void deleteSuspiciousTransferById(Integer id) {
        findSuspiciousTransferById(id);
        cardRepo.deleteById(id);
    }

    @Override
    public SuspiciousCardTransferDTO findSuspiciousTransferById(Integer id) {
        Optional<SuspiciousCardTransfer> transfer = cardRepo.findById(id);
        if (!transfer.isPresent()) {
            String message = String.format("Suspicious card transfer with id=%d has not been found", id);
            logger.error(message);
            throw new SuspiciousTransferNotFoundException(message);
        }
        return mapper.toDTO(transfer.get());
    }

    @Override
    public List<SuspiciousCardTransferDTO> findAllSuspiciousTransfers() {
        return cardRepo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateSuspiciousTransferById(Integer id, SuspiciousCardTransferDTO transfer) {
        SuspiciousCardTransferDTO transferToBeUpdated = findSuspiciousTransferById(id);
        validator.validateTransfer(mapper.toEntity(transfer));
        if (cardRepo.findByCardTransferId(transfer.getCardTransferId()) != null) {
            String message = String.format("Suspicious card transfer with cardTransferId=%d already exists", transfer.getCardTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        transferToBeUpdated.setCardTransferId(transfer.getCardTransferId());
        transferToBeUpdated.setIsSuspicious(transfer.getIsSuspicious());
        transferToBeUpdated.setSuspiciousReason(transfer.getSuspiciousReason());
        transferToBeUpdated.setBlockedReason(transfer.getBlockedReason());
        cardRepo.save(mapper.toEntity(transferToBeUpdated));
    }
}
