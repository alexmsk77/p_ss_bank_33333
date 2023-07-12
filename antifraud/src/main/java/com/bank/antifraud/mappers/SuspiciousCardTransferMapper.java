package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entities.SuspiciousCardTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuspiciousCardTransferMapper {

    SuspiciousCardTransfer toEntity(SuspiciousCardTransferDTO dto);

    SuspiciousCardTransferDTO toDTO(SuspiciousCardTransfer transfer);
}
