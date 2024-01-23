package com.abhay.springjwtauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.UnavailableException;

@RestControllerAdvice
public class UnauthorizedException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity notAuthorizedException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
