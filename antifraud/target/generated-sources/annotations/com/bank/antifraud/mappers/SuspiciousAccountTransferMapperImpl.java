package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.entities.SuspiciousAccountTransfer;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-11T21:19:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.2 (Microsoft)"
)
@Component
public class SuspiciousAccountTransferMapperImpl implements SuspiciousAccountTransferMapper {

    @Override
    public SuspiciousAccountTransfer toEntity(SuspiciousAccountTransferDTO dto) {
        if ( dto == null ) {
            return null;
        }

        SuspiciousAccountTransfer suspiciousAccountTransfer = new SuspiciousAccountTransfer();

        suspiciousAccountTransfer.setIsBlocked( dto.getIsBlocked() );
        suspiciousAccountTransfer.setIsSuspicious( dto.getIsSuspicious() );
        suspiciousAccountTransfer.setBlockedReason( dto.getBlockedReason() );
        suspiciousAccountTransfer.setSuspiciousReason( dto.getSuspiciousReason() );
        suspiciousAccountTransfer.setAccountTransferId( dto.getAccountTransferId() );

        return suspiciousAccountTransfer;
    }

    @Override
    public SuspiciousAccountTransferDTO toDTO(SuspiciousAccountTransfer transfer) {
        if ( transfer == null ) {
            return null;
        }

        SuspiciousAccountTransferDTO suspiciousAccountTransferDTO = new SuspiciousAccountTransferDTO();

        suspiciousAccountTransferDTO.setIsBlocked( transfer.getIsBlocked() );
        suspiciousAccountTransferDTO.setIsSuspicious( transfer.getIsSuspicious() );
        suspiciousAccountTransferDTO.setBlockedReason( transfer.getBlockedReason() );
        suspiciousAccountTransferDTO.setSuspiciousReason( transfer.getSuspiciousReason() );
        suspiciousAccountTransferDTO.setAccountTransferId( transfer.getAccountTransferId() );

        return suspiciousAccountTransferDTO;
    }
}
