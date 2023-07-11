package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.entities.SuspiciousPhoneTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuspiciousPhoneTransferMapper {

    SuspiciousPhoneTransfer toEntity(SuspiciousPhoneTransferDTO dto);

    SuspiciousPhoneTransferDTO toDTO(SuspiciousPhoneTransfer transfer);
}
