package com.bank.transfer.controller;

import com.bank.transfer.aop.AuditOne;
import com.bank.transfer.aop.AuditTwo;
import com.bank.transfer.dto.CardTransferDTO;
import com.bank.transfer.service.CardTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardTransferController {

    private final CardTransferService cardTransferService;

    @AuditOne
    @PostMapping
    public ResponseEntity<HttpStatus> post(@Valid @RequestBody CardTransferDTO cardTransferDTO) {
        cardTransferService.makeTransaction(cardTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @AuditTwo
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        cardTransferService.deleteTransaction(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CardTransferDTO>> getAllTransactions() {
        return ResponseEntity.ok(cardTransferService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardTransferDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(cardTransferService.getTransaction(id));
    }

    @AuditOne
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody CardTransferDTO cardTransferDTO, @PathVariable Long id) {
        cardTransferService.updateTransaction(cardTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
