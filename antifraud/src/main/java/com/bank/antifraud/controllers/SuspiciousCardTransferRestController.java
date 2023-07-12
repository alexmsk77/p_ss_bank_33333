package com.bank.antifraud.controllers;


import com.bank.antifraud.audit.Auditable;
import com.bank.antifraud.dto.SuspiciousCardTransferDTO;
import com.bank.antifraud.services.AbstractSuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
@Tag(name = "Suspicious card transfer controller", description = "Управление подозрительными трнасферами, произведенными с карты")
public class SuspiciousCardTransferRestController {

    private final AbstractSuspiciousTransferService<SuspiciousCardTransferDTO, Integer> service;

    @Operation(summary = "Получение всех подозрительных трансферов")
    @GetMapping
    public List<SuspiciousCardTransferDTO> getAllSuspiciousCardTransfers() {
        return service.findAllSuspiciousTransfers();
    }

    @Operation(summary = "Добавление подозрительного трансфера")
    @Auditable
    @PostMapping
    public SuspiciousCardTransferDTO createSuspiciousTransfer(@RequestBody SuspiciousCardTransferDTO cardTransfer) {
        service.saveSuspiciousTransfer(cardTransfer);
        return cardTransfer;
    }

    @Operation(summary = "Получение подозрительного трансфера по его айди")
    @Auditable
    @GetMapping("/{id}")
    public SuspiciousCardTransferDTO getSuspiciousTransfer(@PathVariable("id") Integer id) {
        return service.findSuspiciousTransferById(id);
    }

    @Operation(summary = "Изменение существующего подозрительного трансфера")
    @Auditable
    @PatchMapping("/{id}")
    public SuspiciousCardTransferDTO updateSuspiciousTransfer(@RequestBody SuspiciousCardTransferDTO cardTransfer, @PathVariable("id") Integer id) {
        service.updateSuspiciousTransferById(id, cardTransfer);
        return cardTransfer;
    }

    @Operation(summary = "Удаление подозрительного трансфера")
    @Auditable
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSuspiciousTransfer(@PathVariable Integer id) {
        service.deleteSuspiciousTransferById(id);
    }
}
