package com.bank.account.mapper;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountDetailsMapper {

    @Mapping(target = "id", ignore = true)
    AccountDetailsEntity toEntity(AccountDetailsDto accountDetails);

    AccountDetailsDto toDto(AccountDetailsEntity accountDetails);

    List<AccountDetailsDto> toDtoList(List<AccountDetailsEntity> accountDetailsList);

    @Mapping(target = "id", ignore = true)
    AccountDetailsEntity mergeToEntity(@MappingTarget AccountDetailsEntity accountDetails, AccountDetailsDto accountDetailsDto);
}
