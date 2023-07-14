package com.bank.transfer.aop;

import com.bank.transfer.model.Audit;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.service.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Класс, описывающий логику заполнения таблицы Audit в БД.
 */
@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;
    private final AuditRepository auditRepository;
    private final Logger log = Logger.getLogger(AuditAspect.class.getName());

    public AuditAspect(AuditService auditService, AuditRepository auditRepository) {
        this.auditService = auditService;
        this.auditRepository = auditRepository;
    }

    @Pointcut("@annotation(com.bank.transfer.aop.AuditOne)")
    public void pointCutOne() {
    }

    @Pointcut("@annotation(com.bank.transfer.aop.AuditTwo)")
    public void pointCutTwo() {

    }

    /**
     * Метод, содержащий логику добавления записей в таблицу Audit для методов POST и PATCH.
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("pointCutOne()")
    public Object addTransactionLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            addTransactionToAuditTable(joinPoint);
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }

    private void addTransactionToAuditTable(JoinPoint joinPoint) throws JsonProcessingException {

        Audit audit = new Audit();
        ObjectMapper objectMapper = new ObjectMapper();

        String entityType = joinPoint.getTarget().getClass()
                .getSimpleName().replaceAll("Controller", "");

        String operationType = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest().getMethod();

        String json = objectMapper.writeValueAsString(joinPoint.getArgs()[0]);

        Long entityID = objectMapper.readTree(json).get("id").longValue();

        Optional<Audit> auditOptional = getAuditEntity(entityType, entityID);

        switch (operationType) {
            case "POST":
                audit.setEntity_json(objectMapper.writeValueAsString(joinPoint.getArgs()[0]));
                audit.setCreated_at(new Timestamp(System.currentTimeMillis()));
                audit.setCreated_by("ADMIN");
                break;

            case "PATCH":
                if(auditOptional.get().getModified_by() == null
                        && auditOptional.get().getModified_at() == null
                        && auditOptional.get().getNew_entity_json() == null) {
                    audit.setCreated_by(auditOptional.get().getCreated_by());
                    audit.setCreated_at(auditOptional.get().getCreated_at());
                    audit.setEntity_json(auditOptional.get().getEntity_json());
                } else if (auditOptional.get().getModified_by() != null
                        && auditOptional.get().getModified_at() != null
                        && auditOptional.get().getNew_entity_json() != null) {
                    audit.setCreated_by(auditOptional.get().getModified_by());
                    audit.setCreated_at(auditOptional.get().getModified_at());
                    audit.setEntity_json(auditOptional.get().getNew_entity_json());
                }
                audit.setModified_by("ADMIN");
                audit.setModified_at(new Timestamp(System.currentTimeMillis()));
                audit.setNew_entity_json(objectMapper.writeValueAsString(joinPoint.getArgs()[0]));
                break;

        }

        audit.setEntity_type(entityType);
        audit.setOperation_type(operationType);

        auditService.saveAuditLogging(audit);
    }

    /**
     *  Метод, содержащий логику добавления записей в таблицу Audit для метода DELETE.
     * @param joinPoint
     * @param id
     */
    @AfterReturning("pointCutTwo() && args(id)")
    public void addDeletedTransactionToAuditTable(JoinPoint joinPoint, Long id) {
        Audit audit = new Audit();
        String entityType = joinPoint.getTarget().getClass()
                .getSimpleName().replaceAll("Controller", "");
        Long entityID = (Long) joinPoint.getArgs()[0];
        String operationType = "DELETE";
        Optional<Audit> optionalAudit = getAuditEntity(entityType, entityID);

        if (optionalAudit.get().getModified_at() == null
                && optionalAudit.get().getModified_by() == null
                && optionalAudit.get().getNew_entity_json() == null) {
            audit.setCreated_at(new Timestamp(System.currentTimeMillis()));
            audit.setCreated_by(optionalAudit.get().getCreated_by());
            audit.setEntity_json(optionalAudit.get().getEntity_json());
        } else {
            audit.setCreated_at(optionalAudit.get().getModified_at());
            audit.setCreated_by(optionalAudit.get().getModified_by());
            audit.setEntity_json(optionalAudit.get().getNew_entity_json());
            audit.setModified_by("ADMIN");
            audit.setModified_at(new Timestamp(System.currentTimeMillis()));
        }
        audit.setEntity_type(entityType);
        audit.setOperation_type(operationType);

        auditService.saveAuditLogging(audit);

    }

    private Optional<Audit> getAuditEntity(String entityType, Long entityID) {
        ObjectMapper objectMapper = new ObjectMapper();
        return auditRepository.findAll().stream()
                .filter(auditEntity -> auditEntity.getEntity_type().equals(entityType))
                .filter(auditEntity -> {
                    try {
                        return objectMapper.readTree(auditEntity.getEntity_json())
                                .get("id").longValue() == entityID;
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .max(Comparator.comparing(Audit::getId));
    }

}
