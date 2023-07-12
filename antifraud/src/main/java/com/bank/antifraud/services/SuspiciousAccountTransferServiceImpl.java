package com.bank.antifraud.services;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.entities.SuspiciousAccountTransfer;
import com.bank.antifraud.exception.SuspiciousTransferNotFoundException;
import com.bank.antifraud.exception.UniqueFieldAlreadyExistsException;
import com.bank.antifraud.mappers.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repositories.SuspiciousAccountTransferRepository;
import com.bank.antifraud.validator.SuspiciousTransferValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Имплементация сервиса для класса SuspiciousAccountTransfer.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SuspiciousAccountTransferServiceImpl implements AbstractSuspiciousTransferService<SuspiciousAccountTransferDTO, Integer> {

    private final SuspiciousAccountTransferRepository accRepo;
    private final SuspiciousAccountTransferMapper mapper;
    private final SuspiciousTransferValidator<SuspiciousAccountTransfer> validator;


    @Override
    public void saveSuspiciousTransfer(SuspiciousAccountTransferDTO dto) {
        if (accRepo.findByAccountTransferId(dto.getAccountTransferId()) != null) {
            String message = String.format("Suspicious account transfer with accountTransferId=%d already exists", dto.getAccountTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        validator.validateTransfer(mapper.toEntity(dto));
        accRepo.save(mapper.toEntity(dto));
    }


    @Override
    public void deleteSuspiciousTransferById(Integer id) {
        findSuspiciousTransferById(id);
        accRepo.deleteById(id);
    }

    @Override
    public SuspiciousAccountTransferDTO findSuspiciousTransferById(Integer id) {
        Optional<SuspiciousAccountTransfer> transfer = accRepo.findById(id);
        if (!transfer.isPresent()) {
            String message = String.format("Suspicious account transfer with id=%d has not been found", id);
            logger.error(message);
            throw new SuspiciousTransferNotFoundException(message);
        }
        return mapper.toDTO(transfer.get());
    }

    @Override
    public List<SuspiciousAccountTransferDTO> findAllSuspiciousTransfers() {
        return accRepo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateSuspiciousTransferById(Integer id, SuspiciousAccountTransferDTO transfer) {
        SuspiciousAccountTransferDTO transferToBeUpdated = findSuspiciousTransferById(id);
        validator.validateTransfer(mapper.toEntity(transfer));
        if (accRepo.findByAccountTransferId(transfer.getAccountTransferId()) != null) {
            String message = String.format("Suspicious account transfer with accountTransferId=%d already exists", transfer.getAccountTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        transferToBeUpdated.setAccountTransferId(transfer.getAccountTransferId());
        transferToBeUpdated.setIsSuspicious(transfer.getIsSuspicious());
        transferToBeUpdated.setSuspiciousReason(transfer.getSuspiciousReason());
        transferToBeUpdated.setBlockedReason(transfer.getBlockedReason());
        accRepo.save(mapper.toEntity(transferToBeUpdated));
    }
}
