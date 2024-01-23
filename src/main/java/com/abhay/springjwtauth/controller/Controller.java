package com.abhay.springjwtauth.controller;

import com.abhay.springjwtauth.config.JwtUserDetailsService;
import com.abhay.springjwtauth.jwtutils.TokenManager;
import com.abhay.springjwtauth.model.JwtRequestModel;
import com.abhay.springjwtauth.model.JwtResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private TokenManager tokenManager;
    @GetMapping("/hello")
    public String hello() {
        return "Hello there";
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception {
        try{
            AuthenticationManager authenticationManager = authentication ->
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        }catch (DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));

    }
}