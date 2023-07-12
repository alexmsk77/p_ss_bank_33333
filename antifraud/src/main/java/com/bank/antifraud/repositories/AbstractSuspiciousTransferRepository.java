package com.bank.antifraud.repositories;

import com.bank.antifraud.entities.AbstractSuspiciousTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Репозиторий, который нужен для наследования тремя репозиторями подозрительных трансферов, связанными с БД.
 * @param <T> - дженерик для классов подозрительных трансферов.
 * @param <ID> - дженерик для ID сущностей подозрительных трансферов.
 */
@NoRepositoryBean
public interface AbstractSuspiciousTransferRepository<T extends AbstractSuspiciousTransfer, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
