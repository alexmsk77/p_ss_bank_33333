package com.bank.antifraud.services;


import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.entities.SuspiciousPhoneTransfer;
import com.bank.antifraud.exception.SuspiciousTransferNotFoundException;
import com.bank.antifraud.exception.UniqueFieldAlreadyExistsException;
import com.bank.antifraud.mappers.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repositories.SuspiciousPhoneTransferRepository;
import com.bank.antifraud.validator.SuspiciousTransferValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Имплементация сервиса для класса SuspiciousPhoneTransfer.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class SuspiciousPhoneTransferServiceImpl implements AbstractSuspiciousTransferService<SuspiciousPhoneTransferDTO, Integer> {

    private final SuspiciousPhoneTransferRepository phoneRepo;

    private final SuspiciousPhoneTransferMapper mapper;
    private final SuspiciousTransferValidator<SuspiciousPhoneTransfer> validator;

    @Override
    public void saveSuspiciousTransfer(SuspiciousPhoneTransferDTO dto) {
        if (phoneRepo.findByPhoneTransferId(dto.getPhoneTransferId()) != null) {
            String message = String.format("Suspicious phone transfer with phoneTransferId=%d already exists", dto.getPhoneTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        validator.validateTransfer(mapper.toEntity(dto));
        phoneRepo.save(mapper.toEntity(dto));
    }


    @Override
    public void deleteSuspiciousTransferById(Integer id) {
        findSuspiciousTransferById(id);
        phoneRepo.deleteById(id);
    }

    @Override
    public SuspiciousPhoneTransferDTO findSuspiciousTransferById(Integer id) {
        Optional<SuspiciousPhoneTransfer> transfer = phoneRepo.findById(id);
        if (!transfer.isPresent()) {
            String message = String.format("Suspicious phone transfer with id=%d has not been found", id);
            logger.error(message);
            throw new SuspiciousTransferNotFoundException(message);
        }
        return mapper.toDTO(transfer.get());
    }

    @Override
    public List<SuspiciousPhoneTransferDTO> findAllSuspiciousTransfers() {
        return phoneRepo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void updateSuspiciousTransferById(Integer id, SuspiciousPhoneTransferDTO transfer) {
        SuspiciousPhoneTransferDTO transferToBeUpdated = findSuspiciousTransferById(id);
        validator.validateTransfer(mapper.toEntity(transfer));
        if (phoneRepo.findByPhoneTransferId(transfer.getPhoneTransferId()) != null) {
            String message = String.format("Suspicious phone transfer with phoneTransferId=%d already exists", transfer.getPhoneTransferId());
            logger.error(message);
            throw new UniqueFieldAlreadyExistsException(message);
        }
        transferToBeUpdated.setPhoneTransferId(transfer.getPhoneTransferId());
        transferToBeUpdated.setIsSuspicious(transfer.getIsSuspicious());
        transferToBeUpdated.setSuspiciousReason(transfer.getSuspiciousReason());
        transferToBeUpdated.setBlockedReason(transfer.getBlockedReason());
        phoneRepo.save(mapper.toEntity(transferToBeUpdated));
    }
}
