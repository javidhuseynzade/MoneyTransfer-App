package com.example.TransactionMS.service;

import com.example.TransactionMS.dto.TransactionDto;
import com.example.TransactionMS.entity.Transaction;
import com.example.TransactionMS.exception.AccountNotFoundException;
import com.example.TransactionMS.exception.InsufficientBalanceException;
import com.example.TransactionMS.model.Account;
import com.example.TransactionMS.repository.TransactionRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountServiceClient accountServiceClient;

    public Transaction createTransaction(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(dto.getFromAccount());
        transaction.setToAccount(dto.getToAccount());
        transaction.setAmount(dto.getAmount());
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id).get();
    }
    public String deleteTransactionById(Long id) {
        transactionRepository.deleteById(id);
        return "Transaction is deleted";
    }
    public Account increaseBalance(Long id, TransactionDto dto) {
        try {
            Account account = accountServiceClient.findAccountById(id);
            account.setBalance(account.getBalance()+dto.getAmount());
            return accountServiceClient.updateAccountById(id,account);
        }
        catch (FeignException e) {
            throw new AccountNotFoundException("Account not found");
        }
    }
    public Account decreaseBalance(Long id, TransactionDto dto) {
        try {
            Account account = accountServiceClient.findAccountById(id);
            if (account.getBalance() >= dto.getAmount()) {
                account.setBalance(account.getBalance()-dto.getAmount());
                return accountServiceClient.updateAccountById(id,account);
            }
            else
                throw new InsufficientBalanceException("Insufficient Balance");
        }
        catch (FeignException e) {
            throw new AccountNotFoundException("Account not found");
        }
    }
    public Account transferBalance(TransactionDto dto) {
        try {
            Account fromAccount = accountServiceClient.findAccountById(dto.getFromAccount());
            Account toAccount = accountServiceClient.findAccountById(dto.getToAccount());
            fromAccount.setBalance(fromAccount.getBalance()-dto.getAmount());
            toAccount.setBalance(toAccount.getBalance()+dto.getAmount());
            accountServiceClient.updateAccountById(dto.getToAccount(),toAccount);
            return accountServiceClient.updateAccountById(dto.getFromAccount(),fromAccount);
        } catch (FeignException e) {
            throw new AccountNotFoundException("Account not found");
        }
    }
}
