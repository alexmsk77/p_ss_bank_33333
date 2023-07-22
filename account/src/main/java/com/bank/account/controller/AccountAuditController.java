package com.bank.account.controller;

import com.bank.account.dto.AuditDto;
import com.bank.account.service.AccountAuditService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AccountAuditController {
    private final AccountAuditService service;
    @Timed("controller: createAuthToken")
    @GetMapping("/{id}")
    public AuditDto read(@PathVariable("id") Long id) {
        return service.findById(id);
    }
}
