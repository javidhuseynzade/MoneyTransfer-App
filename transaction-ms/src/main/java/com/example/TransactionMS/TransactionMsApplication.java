package com.example.TransactionMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TransactionMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionMsApplication.class, args);
	}

}
