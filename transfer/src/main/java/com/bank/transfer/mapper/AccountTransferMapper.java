package com.bank.transfer.mapper;

import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.model.AccountTransfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountTransferMapper {

    AccountTransferDTO accountToDto(AccountTransfer accountTransfer);

    AccountTransfer accountDtoToAccount(AccountTransferDTO accountTransferDTO);
}
