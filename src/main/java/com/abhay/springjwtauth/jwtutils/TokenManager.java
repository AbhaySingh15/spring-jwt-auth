package com.abhay.springjwtauth.jwtutils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// This class is used to perform all jwt actions like generating, validating the token etc.
@Component
public class TokenManager implements Serializable {
    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 10 * 60 * 60;

    @Value("${secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails) {
        // making empty claims map which we will populate later during token creating
        Map<String, Object> claims = new HashMap<>();
        // using builder pattern to set claims like subject, issue date,
        // expiration etc.
        // we are signing the token with algorithm HS512 in this case
        // and a secret key
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    // We need token + user details to validate the token
    public Boolean validateJwtToken(String token, UserDetails userDetails) {
        // getting username from token
        String username = getUsernameFromToken(token);
        // getting the claims from token
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        // checking if token is expired or not
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        // if token is not expired and username from the token matches the username from
        // our user details
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }
    public String getUsernameFromToken(String token) {
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}

