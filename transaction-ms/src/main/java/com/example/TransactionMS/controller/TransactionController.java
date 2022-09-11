package com.example.TransactionMS.controller;

import com.example.TransactionMS.dto.TransactionDto;
import com.example.TransactionMS.entity.Transaction;
import com.example.TransactionMS.model.Account;
import com.example.TransactionMS.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/")
    public Transaction createTransaction(@RequestBody TransactionDto dto) {
        return transactionService.createTransaction(dto);
    }
    @PostMapping("/increase/{accountId}")
    public Account increaseAccountByAccountId(@PathVariable("accountId") Long id,@RequestBody TransactionDto dto) {
        transactionService.createTransaction(dto);
        return transactionService.increaseBalance(id,dto);
    }
    @PostMapping("/decrease/{accountId}")
    public Account decreaseAccountByAccountId(@PathVariable("accountId") Long id,@RequestBody TransactionDto dto) {
        transactionService.createTransaction(dto);
        return transactionService.decreaseBalance(id,dto);
    }
    @PostMapping("/transfer")
    public Account transfer(@RequestBody TransactionDto dto) {
        transactionService.createTransaction(dto);
        return transactionService.transferBalance(dto);
    }
    @GetMapping("/")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }
    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable("transactionId") Long id) {
        return transactionService.findTransactionById(id);
    }
    @DeleteMapping("/{transactionId}")
    public String deleteTransactionById(@PathVariable("transactionId") Long id) {
        return transactionService.deleteTransactionById(id);
    }
}
