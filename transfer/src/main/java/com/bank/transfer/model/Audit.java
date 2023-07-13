package com.bank.transfer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Класс необхоимый для описания аудита всех денежных транзакций.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "audit", schema = "transfer")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    private String entity_type;
    private String operation_type;
    private String created_by;
    private String modified_by;
    private Timestamp created_at;
    private Timestamp modified_at;
    private String new_entity_json;
    private String entity_json;


}
