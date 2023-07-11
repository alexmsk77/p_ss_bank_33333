package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.entities.SuspiciousCardTransfer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-11T21:19:27+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Microsoft)"
)
@Component
public class SuspiciousCardTransferMapperImpl implements SuspiciousCardTransferMapper {

    @Override
    public SuspiciousCardTransfer toEntity(SuspiciousCardTransferDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SuspiciousCardTransfer suspiciousCardTransfer = new SuspiciousCardTransfer();

        suspiciousCardTransfer.setIsBlocked( dto.getIsBlocked() );
        suspiciousCardTransfer.setIsSuspicious( dto.getIsSuspicious() );
        suspiciousCardTransfer.setBlockedReason( dto.getBlockedReason() );
        suspiciousCardTransfer.setSuspiciousReason( dto.getSuspiciousReason() );
        suspiciousCardTransfer.setCardTransferId( dto.getCardTransferId() );

        return suspiciousCardTransfer;
    }

    @Override
    public SuspiciousCardTransferDTO toDTO(SuspiciousCardTransfer transfer) {
        if ( transfer == null ) {
            return null;
        }

        SuspiciousCardTransferDTO suspiciousCardTransferDTO = new SuspiciousCardTransferDTO();

        suspiciousCardTransferDTO.setIsBlocked( transfer.getIsBlocked() );
        suspiciousCardTransferDTO.setIsSuspicious( transfer.getIsSuspicious() );
        suspiciousCardTransferDTO.setBlockedReason( transfer.getBlockedReason() );
        suspiciousCardTransferDTO.setSuspiciousReason( transfer.getSuspiciousReason() );
        suspiciousCardTransferDTO.setCardTransferId( transfer.getCardTransferId() );

        return suspiciousCardTransferDTO;
    }
}
