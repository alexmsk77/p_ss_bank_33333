package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.entities.SuspiciousPhoneTransfer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-11T21:19:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Microsoft)"
)
@Component
public class SuspiciousPhoneTransferMapperImpl implements SuspiciousPhoneTransferMapper {

    @Override
    public SuspiciousPhoneTransfer toEntity(SuspiciousPhoneTransferDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SuspiciousPhoneTransfer suspiciousPhoneTransfer = new SuspiciousPhoneTransfer();

        suspiciousPhoneTransfer.setIsBlocked( dto.getIsBlocked() );
        suspiciousPhoneTransfer.setIsSuspicious( dto.getIsSuspicious() );
        suspiciousPhoneTransfer.setBlockedReason( dto.getBlockedReason() );
        suspiciousPhoneTransfer.setSuspiciousReason( dto.getSuspiciousReason() );
        suspiciousPhoneTransfer.setPhoneTransferId( dto.getPhoneTransferId() );

        return suspiciousPhoneTransfer;
    }

    @Override
    public SuspiciousPhoneTransferDTO toDTO(SuspiciousPhoneTransfer transfer) {
        if ( transfer == null ) {
            return null;
        }

        SuspiciousPhoneTransferDTO suspiciousPhoneTransferDTO = new SuspiciousPhoneTransferDTO();

        suspiciousPhoneTransferDTO.setIsBlocked( transfer.getIsBlocked() );
        suspiciousPhoneTransferDTO.setIsSuspicious( transfer.getIsSuspicious() );
        suspiciousPhoneTransferDTO.setBlockedReason( transfer.getBlockedReason() );
        suspiciousPhoneTransferDTO.setSuspiciousReason( transfer.getSuspiciousReason() );
        suspiciousPhoneTransferDTO.setPhoneTransferId( transfer.getPhoneTransferId() );

        return suspiciousPhoneTransferDTO;
    }
}
