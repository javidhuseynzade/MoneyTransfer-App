package com.example.AccountMS.model;

import lombok.Data;

import javax.persistence.*;

@Data
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
}
