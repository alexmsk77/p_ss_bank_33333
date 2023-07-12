package com.bank.antifraud.audit;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit", schema = "anti_fraud")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 40)
    @NotNull
    @Column(name = "entity_type")
    private String entityType;

    @Size(max = 255)
    @NotNull
    @Column(name = "operation_type")
    private String operationType;

    @Size(max = 255)
    @NotNull
    @Column(name = "created_by")
    private String createdBy;

    @Size(max = 255)
    @Column(name = "modified_by")
    private String modifiedBy;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "new_entity_json")
    private String newEntityJson;

    @NotNull
    @Column(name = "entity_json")
    private String entityJson;
}
