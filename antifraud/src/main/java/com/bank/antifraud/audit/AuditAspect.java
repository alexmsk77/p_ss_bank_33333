package com.bank.antifraud.audit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Класс для конфигурирования аудита.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditService service;
    private final Logger logger = LoggerFactory.getLogger(AuditAspect.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Pointcut("@annotation(com.bank.antifraud.audit.Auditable)")
    public void pointcut() {
    }

    /**
     * Метод, который при затрагивании основных сущностей добавляет в таблицу аудита всю информацию об этом.
     * Срабатывает после возвращения помеченными аннотацией @Auditable методами значений.
     * @param point - объект класса для работы с аспектами.
     * @param dto - объект, который возвращает аудируемый метод.
     * @throws JsonProcessingException - возможная ошибка при парсинге из объекта Java в JSON.
     */
    @AfterReturning(value = "pointcut()", returning = "dto")
    public void getAudit(JoinPoint point, Object dto) throws JsonProcessingException {
        Audit audit = new Audit();
        audit.setCreatedBy("ADMIN");
        audit.setCreatedAt(LocalDateTime.now());

        if (point.getTarget().getClass().getSimpleName().equals("SuspiciousAccountTransferRestController")) {
            audit.setEntityType("SuspiciousAccountTransfer");
        } else if (point.getTarget().getClass().getSimpleName().equals("SuspiciousCardTransferRestController")) {
            audit.setEntityType("SuspiciousCardTransfer");
        } else {
            audit.setEntityType("SuspiciousPhoneTransfer");
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        audit.setOperationType(request.getMethod());

        if (request.getMethod().equals("PATCH")) {
            audit.setModifiedBy("ADMIN");
            audit.setModifiedAt(LocalDateTime.now());
        }

        if (request.getMethod().equals("POST")) {
            audit.setNewEntityJson(objectMapper.writeValueAsString(dto));
        }

        if (request.getMethod().equals("DELETE")) {
            Object [] args = point.getArgs();
            audit.setEntityJson(String.format("{\"id\":%s}", args));
        } else {
            audit.setEntityJson(objectMapper.writeValueAsString(dto));
        }

        service.saveAudit(audit);
    }


    /**
     * Метод для логирования всех методов, связанных с БД, приложения.
     * @param point - объект класса для работы с аспектами.
     */
    @Before("execution(* com.bank.antifraud.repositories.*.*(..))")
    public void methodLogger(JoinPoint point) {
        logger.info(point.getSignature().getName());
    }
}
