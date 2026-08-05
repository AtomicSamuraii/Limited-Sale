package com.AtomicSamurai.LimitedSale.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtService {
    private static final String SECRET = "k2Ub0V2RvUiEdDsHIdZKjLobKZVMqjje48m8Usm1QGT";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_MS = 60 * 60 * 1000;

    public String generateToken(String email, List<String> roles){
        return Jwts.builder().subject(email).claim("roles", roles).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + +EXPIRATION_MS)).signWith(key,Jwts.SIG.HS256).compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = extractClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
