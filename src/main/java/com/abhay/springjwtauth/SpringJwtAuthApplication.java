package com.abhay.springjwtauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;

@SpringBootApplication
public class SpringJwtAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJwtAuthApplication.class, args);
    }

}
