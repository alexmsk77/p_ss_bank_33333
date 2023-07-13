package com.bank.transfer.controller;

import com.bank.transfer.aop.AuditOne;
import com.bank.transfer.aop.AuditTwo;
import com.bank.transfer.dto.PhoneTransferDTO;
import com.bank.transfer.service.PhoneTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phone")
public class PhoneTransferController {

    private final PhoneTransferService phoneTransferService;

    @AuditOne
    @PostMapping
    public ResponseEntity<HttpStatus> post(@Valid @RequestBody PhoneTransferDTO phoneTransferDTO) {
        phoneTransferService.makeTransaction(phoneTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @AuditTwo
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        phoneTransferService.deleteTransaction(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PhoneTransferDTO>> getAllTransactions() {
        return ResponseEntity.ok(phoneTransferService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneTransferDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(phoneTransferService.getTransaction(id));
    }

    @AuditOne
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody PhoneTransferDTO phoneTransferDTO, @PathVariable Long id) {
        phoneTransferService.updateTransaction(phoneTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
