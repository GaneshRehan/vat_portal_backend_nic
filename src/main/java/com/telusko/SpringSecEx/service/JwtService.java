package com.telusko.SpringSecEx.service;


import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretkey="";

    public JwtService(){
        try{
        KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
           SecretKey sk=keyGen.generateKey();
           secretkey=Base64.getEncoder().encodeToString(sk.getEncoded());
        }
        catch(NoSuchAlgorithmException e){
           throw new RuntimeException(e);
        }
    }

    public String generateToken(String username){

        //key is always string but value can be any data type
        Map<String,Object> claims=new HashMap<>();

        return Jwts.builder()
                   .claims()
                   .add(claims)
                   .subject(username)
                   .issuedAt(new Date(System.currentTimeMillis()))
                   .expiration(new Date(System.currentTimeMillis()+60*60*30)) // expiration in 30
                   .and()  //also we want to sign it
                   .signWith(getKey()) //Generate key here
                   .compact();

        
    }

    private SecretKey getKey(){
        byte[] keyBytes=Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
      return extractClaim(token,claims->claims.getSubject());
    }

    private <T> T extractClaim(String token,Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                   .verifyWith(getKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();

    }

    public boolean validateToken(String token,UserDetails userDetails){
        final String userName=extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,claims->claims.getExpiration());
    }


}
