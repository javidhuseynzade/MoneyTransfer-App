package com.example.AccountMS.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String currency;
    private Double balance;
}
