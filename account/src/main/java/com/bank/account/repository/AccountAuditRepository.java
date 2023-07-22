package com.bank.account.repository;

import com.bank.account.entity.AuditEntity;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountAuditRepository extends JpaRepository<AuditEntity, Long> {
}
