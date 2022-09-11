package com.example.CustomerMS.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table (name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phone;
}
