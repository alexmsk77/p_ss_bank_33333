package com.bank.account.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDetailsDto implements Serializable {
    Long id;
    Long passportId;
    Long accountNumber;
    Long bankDetailsId;
    BigDecimal money;
    Boolean negativeBalance;
    Long profileId;
}
