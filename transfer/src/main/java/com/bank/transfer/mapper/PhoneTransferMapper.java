package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.model.CardTransfer;
import com.bank.transfer.model.PhoneTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneTransferMapper {

    PhoneTransferDTO phoneToDto(PhoneTransfer phoneTransfer);
    PhoneTransfer phoneDtoToPhone(PhoneTransferDTO phoneTransferDTO);
}
