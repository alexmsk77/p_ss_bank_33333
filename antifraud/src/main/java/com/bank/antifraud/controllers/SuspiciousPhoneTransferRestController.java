package com.bank.antifraud.controllers;


import com.bank.antifraud.audit.Auditable;
import com.bank.antifraud.dto.SuspiciousPhoneTransferDTO;
import com.bank.antifraud.services.AbstractSuspiciousTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
@RequiredArgsConstructor
@Tag(name = "Suspicious phone transfer controller", description = "Управление подозрительными трнасферами, произведенными с телефона")
public class SuspiciousPhoneTransferRestController {

    private final AbstractSuspiciousTransferService<SuspiciousPhoneTransferDTO, Integer> service;

    @Operation(summary = "Получение всех подозрительных трансферов")
    @GetMapping
    public List<SuspiciousPhoneTransferDTO> getAllSuspiciousPhoneTransfers() {
        return service.findAllSuspiciousTransfers();
    }

    @Operation(summary = "Добавление подозрительного трансфера")
    @Auditable
    @PostMapping
    public SuspiciousPhoneTransferDTO createSuspiciousTransfer(@RequestBody SuspiciousPhoneTransferDTO phoneTransfer) {
        service.saveSuspiciousTransfer(phoneTransfer);
        return phoneTransfer;
    }

    @Operation(summary = "Получение подозрительного трансфера по его айди")
    @Auditable
    @GetMapping("/{id}")
    public SuspiciousPhoneTransferDTO getSuspiciousTransfer(@PathVariable("id") Integer id) {
        return service.findSuspiciousTransferById(id);
    }

    @Operation(summary = "Изменение существующего подозрительного трансфера")
    @Auditable
    @PatchMapping("/{id}")
    public SuspiciousPhoneTransferDTO updateSuspiciousTransfer(@RequestBody SuspiciousPhoneTransferDTO phoneTransfer, @PathVariable("id") Integer id) {
        service.updateSuspiciousTransferById(id, phoneTransfer);
        return phoneTransfer;
    }

    @Operation(summary = "Удаление подозрительного трансфера")
    @Auditable
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSuspiciousTransfer(@PathVariable Integer id) {
        service.deleteSuspiciousTransferById(id);
    }
}
