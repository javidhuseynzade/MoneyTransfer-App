package com.example.AccountMS.service;

import com.example.AccountMS.entity.Account;
import com.example.AccountMS.exception.AccountNotFoundException;
import com.example.AccountMS.model.Customer;
import com.example.AccountMS.model.MailInfo;
import com.example.AccountMS.repository.AccountRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerServiceClient customerServiceClient;
    private final RabbitTemplate rabbitTemplate;
    public Account createAccount(Account account) {
        try {
            Customer customer = findCustomerByCustomerId(account.getCustomerId());
            MailInfo mailInfo = new MailInfo();
            mailInfo.setRecipient(customer.getEmail());
            mailInfo.setSubject("Simple Email");
            mailInfo.setMsgBody("Your account has been created successfully!");
            rabbitTemplate.convertAndSend("mail-sender-queue",mailInfo);
            return accountRepository.save(account);
        } catch (FeignException e) {
            throw new AccountNotFoundException(String.format("Customer not found with id:%d",account.getCustomerId()));
        }
    }
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
    public Account findAccountById(Long id) {
        return accountRepository.findById(id).get();
    }
    public Customer findCustomerByCustomerId(Long customerId) {
        return customerServiceClient.findCustomerById(customerId);
    }
    public Account updateAccountById(Long id,Account newAccount) {
        Account account = accountRepository.findById(id).get();
        account.setCurrency(newAccount.getCurrency());
        account.setBalance(newAccount.getBalance());
        MailInfo mailInfo = new MailInfo();
        mailInfo.setRecipient(findCustomerByCustomerId(account.getCustomerId()).getEmail());
        mailInfo.setSubject("Simple Email");
        mailInfo.setMsgBody("Your account has been updated successfully!");
        rabbitTemplate.convertAndSend("mail-sender-queue",mailInfo);
        return accountRepository.save(account);
    }
    public String deleteAccountById(Long id) {
        accountRepository.deleteById(id);
        return "Account is deleted";
    }
}
