package com.example.CustomerMS.service;

import com.example.CustomerMS.entity.Customer;
import com.example.CustomerMS.exception.AccountNotFoundException;
import com.example.CustomerMS.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }
    public Customer findCustomerById(Long id) {
        try {
            return customerRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new AccountNotFoundException(String.format("Customer not found with id:%d",id));
        }
    }
    public Customer updateCustomerById(Long id,Customer newCustomer) {
        Customer customer = customerRepository.findById(id).get();
        customer.setName(newCustomer.getName());
        customer.setEmail(newCustomer.getEmail());
        customer.setAddress(newCustomer.getAddress());
        customer.setPhone(newCustomer.getPhone());
        return customerRepository.save(customer);
    }
    public String deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
        return "Customer is deleted";
    }
}
