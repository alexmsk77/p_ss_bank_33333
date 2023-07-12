package com.bank.antifraud.controllers;

import com.bank.antifraud.audit.Auditable;
import com.bank.antifraud.dto.SuspiciousAccountTransferDTO;
import com.bank.antifraud.services.AbstractSuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "Suspicious account transfer controller", description = "Управление подозрительными трнасферами, произведенными с аккаунта")
public class SuspiciousAccountTransferRestController {

    private final AbstractSuspiciousTransferService<SuspiciousAccountTransferDTO, Integer> service;

    @Operation(summary = "Получение всех подозрительных трансферов")
    @GetMapping
    public List<SuspiciousAccountTransferDTO> getAllSuspiciousAccountTransfers() {
        return service.findAllSuspiciousTransfers();
    }

    @Operation(summary = "Добавление подозрительного трансфера")
    @Auditable
    @PostMapping
    public SuspiciousAccountTransferDTO createSuspiciousTransfer(@RequestBody SuspiciousAccountTransferDTO accTransfer) {
        service.saveSuspiciousTransfer(accTransfer);
        return accTransfer;
    }

    @Operation(summary = "Получение подозрительного трансфера по его айди")
    @Auditable
    @GetMapping("/{id}")
    public SuspiciousAccountTransferDTO getSuspiciousTransfer(@PathVariable("id") Integer id) {
        return service.findSuspiciousTransferById(id);
    }

    @Operation(summary = "Изменение существующего подозрительного трансфера")
    @Auditable
    @PatchMapping("/{id}")
    public SuspiciousAccountTransferDTO updateSuspiciousTransfer(@RequestBody SuspiciousAccountTransferDTO accTransfer, @PathVariable("id") Integer id) {
        service.updateSuspiciousTransferById(id, accTransfer);
        return accTransfer;
    }

    @Operation(summary = "Удаление подозрительного трансфера")
    @Auditable
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSuspiciousTransfer(@PathVariable Integer id) {
        service.deleteSuspiciousTransferById(id);
    }
}
