package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.service.AccountDetailsService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;




@Timed("repository: findUserByProfileId")
@RestController
@RequiredArgsConstructor
@RequestMapping("/details")

public class AccountDetailsController {

    private final AccountDetailsService service;

    @Timed("controller: getUnsecuredData")
    @GetMapping("/{id}")
    public AccountDetailsDto read(@PathVariable("id") Long id) {
        return service.findById(id);
    }
    @Timed("controller: createAuthToken")
    @PostMapping("/create")
    public ResponseEntity<AccountDetailsDto> create(@RequestBody AccountDetailsDto accountDetails) {
        return ResponseEntity.ok(service.save(accountDetails));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDetailsDto> update(@PathVariable Long id,
                                                    @RequestBody AccountDetailsDto accountDetails) {
        return ResponseEntity.ok(service.update(id, accountDetails));
    }


    @GetMapping("read/all")
    public ResponseEntity<List<AccountDetailsDto>> readAll(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }
}
