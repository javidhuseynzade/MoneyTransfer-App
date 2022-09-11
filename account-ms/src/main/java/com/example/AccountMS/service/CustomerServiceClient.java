package com.example.AccountMS.service;

import com.example.AccountMS.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "feignAccount", url = "http://localhost:8080/customer")
public interface CustomerServiceClient {
    @GetMapping("/{customerId}")
    public Customer findCustomerById(@PathVariable("customerId") Long customerId);
}
