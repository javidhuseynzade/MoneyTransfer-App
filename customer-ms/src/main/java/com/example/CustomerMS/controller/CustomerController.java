package com.example.CustomerMS.controller;

import com.example.CustomerMS.entity.Customer;
import com.example.CustomerMS.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }
    @GetMapping("/")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
    @GetMapping("/{customerId}")
    public Customer findCustomerById(@PathVariable("customerId") Long id) {
        return customerService.findCustomerById(id);
    }
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable("customerId") Long id,@RequestBody Customer customer) {
        return customerService.updateCustomerById(id,customer);
    }
    @DeleteMapping("/{customerId}")
    public String deleteCustomerById(@PathVariable("customerId") Long id) {
        return customerService.deleteCustomerById(id);
    }
}
