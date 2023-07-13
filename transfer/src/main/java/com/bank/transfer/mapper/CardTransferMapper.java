package com.bank.transfer.mapper;

import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.model.CardTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTransferMapper {
    CardTransferDTO cardToDto(CardTransfer cardTransfer);
    CardTransfer cardDtoToCard(CardTransferDTO cardTransferDTO);
}
