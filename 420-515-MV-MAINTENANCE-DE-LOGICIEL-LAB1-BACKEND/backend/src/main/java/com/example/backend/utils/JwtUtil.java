package com.example.backend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {


    private static final String SECRET_KEY = "CleTropMasterclassPourJWT1234567";


    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    public static String creerToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public static String verifierToken(String token) {
        try {
            System.out.println("cle valide");
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

        } catch (ExpiredJwtException e) {
            System.out.println(" Token expiré ");
            return null;
        } catch (JwtException e) {
            System.out.println(" Token invalide ");
            return null;
        }
    }


}
