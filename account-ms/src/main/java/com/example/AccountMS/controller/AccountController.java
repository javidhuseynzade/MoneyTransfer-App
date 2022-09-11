package com.example.AccountMS.controller;

import com.example.AccountMS.entity.Account;
import com.example.AccountMS.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
    @GetMapping("/")
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }
    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable("accountId") Long id) {
        return accountService.findAccountById(id);
    }
    @PutMapping("/{accountId}")
    public Account updateAccountById(@PathVariable("accountId") Long id,@RequestBody Account account) {
        return accountService.updateAccountById(id,account);
    }
    @DeleteMapping("/{accountId}")
    public String deleteAccountById(@PathVariable("accountId") Long id) {
        return accountService.deleteAccountById(id);
    }
}
