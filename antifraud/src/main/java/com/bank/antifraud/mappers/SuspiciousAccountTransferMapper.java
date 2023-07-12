package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.entities.SuspiciousAccountTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuspiciousAccountTransferMapper {

    SuspiciousAccountTransfer toEntity(SuspiciousAccountTransferDTO dto);

    SuspiciousAccountTransferDTO toDTO(SuspiciousAccountTransfer transfer);
}
