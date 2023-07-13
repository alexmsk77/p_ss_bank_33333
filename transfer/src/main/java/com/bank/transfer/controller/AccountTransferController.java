package com.bank.transfer.controller;
import com.bank.transfer.aop.AuditOne;
import com.bank.transfer.aop.AuditTwo;
import com.bank.transfer.dto.AccountTransferDTO;
import com.bank.transfer.service.AccountTransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Tag(
        name = "CRUD REST APIs for AccountTransfer Resource",
        description = "CRUD REST APIs - Create, Update, Get, Get All, Delete AccountTransfer")
public class AccountTransferController {

    private final AccountTransferService accountTransferService;

    @Operation(
            summary = "Create AccountTransfer REST API",
            description = "Create AccountTransfer REST API is used to save accountTransfer in a database"
    )
    @AuditOne
    @PostMapping
    public ResponseEntity<HttpStatus> post(@Valid @RequestBody AccountTransferDTO accountTransferDTO) {
        accountTransferService.makeTransaction(accountTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Delete AccountTransfer REST API",
            description = "Delete AccountTransfer REST API is used to delete an existing accountTransfer from the database"
    )
    @AuditTwo
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        accountTransferService.deleteTransaction(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Get all AccountTransfers REST API",
            description = "Get all AccountTransfers REST API is used to get all accountTransfers from the database"
    )
    @GetMapping
    public ResponseEntity<List<AccountTransferDTO>> getAllAccountTransfers() {
        return ResponseEntity.ok(accountTransferService.getAllTransactions());
    }


    @Operation(
            summary = "Get AccountTransfer By ID REST API",
            description = "Get AccountTransfer By ID REST API is used to get a single accountTransfer from the database"
    )
    @GetMapping("/{id}")
    public ResponseEntity<AccountTransferDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(accountTransferService.getTransaction(id));
    }

    @Operation(
            summary = "Update AccountTransfer REST API",
            description = "Update AccountTransfer REST API is used to update an existing accountTransfer in the database"
    )
    @AuditOne
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @RequestBody AccountTransferDTO accountTransferDTO, @PathVariable Long id) {
        accountTransferService.updateTransaction(accountTransferDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
