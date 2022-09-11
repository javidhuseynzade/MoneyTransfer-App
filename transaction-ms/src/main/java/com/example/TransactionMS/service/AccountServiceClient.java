package com.example.TransactionMS.service;

import com.example.TransactionMS.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "feignTransaction", url = "http://localhost:8081/account")
public interface AccountServiceClient {
    @GetMapping("/{accountId}")
    public Account findAccountById(@PathVariable("accountId") Long id);

    @PutMapping("/{accountId}")
    public Account updateAccountById(@PathVariable("accountId") Long id, @RequestBody Account account);
}
