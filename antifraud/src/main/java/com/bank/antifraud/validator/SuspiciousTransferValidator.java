package com.bank.antifraud.validator;

import com.bank.antifraud.audit.AuditAspect;
import com.bank.antifraud.entities.AbstractSuspiciousTransfer;
import com.bank.antifraud.exception.SuspiciousTransferNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Общий класс валидации для всех подозрительных трансферов.
 * @param <Transfer> - дженерик для классов подозрительных трансферов.
 */
@Component
public class SuspiciousTransferValidator<Transfer extends AbstractSuspiciousTransfer> {

    Logger logger = LoggerFactory.getLogger(AuditAspect.class.getName());

    /**
     * Метод, который занимается валидацией подозрительных трансферов при их доавлении/обновлении в БД.
     * @param transfer - принимает подозрительный трансфер, являющийся объектом одного из трех классов-сущностей.
     */
    public void validateTransfer(Transfer transfer) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();
        Set<ConstraintViolation<Transfer>> violations = validator.validate(transfer);
        String message = "validation failed! Only blockedReason field can be null";
        if (!violations.isEmpty()) {
            logger.error(message);
            throw new SuspiciousTransferNotValidException(message);
        }
    }
}
