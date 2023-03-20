package com.caito.authservice.security;


import com.caito.authservice.dto.RequestDTO;
import com.caito.authservice.entity.AuthUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private RouteValidator routeValidator;


    /*@PostConstruct
    protected void init(){

        byte[] secretbytes = Decoders.BASE64.decode(secret);
         KEY_SECRET = Keys.hmacShaKeyFor(secretbytes);
    }*/

    public String createToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(authUser.getUsername());
        claims.put("id", authUser.getId());
        claims.put("role", authUser.getRole());
        Date now = new Date();
        Date exp = new Date(now.getTime() + 3600000) ;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(this.getKey(secret))
                .compact();
    }

    public boolean validate(String token, RequestDTO requestDTO){
        try {
            Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build().parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        if (!this.isAdmin(token) && routeValidator.isAdmin(requestDTO)){
            return false;
        }
        return true;
    }

    public String getUsernameFromToken(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build()
                    .parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return ("token invalido");
        }
    }

    public Key getKey(String secret){
        byte[] secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    private boolean isAdmin(String token){
        return Jwts.parserBuilder().setSigningKey(this.getKey(secret)).build().parseClaimsJws(token)
                .getBody().get("role").equals("admin");
    }
}
