package com.theagilemonkeys.crmapi.security.jwt;

import com.theagilemonkeys.crmapi.models.ERole;
import com.theagilemonkeys.crmapi.models.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JWTProvider {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.duration}")
    private long jwtDurationInSeconds;
    
    public String generateToken(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        
        Date expiration = new Date(System.currentTimeMillis() + this.jwtDurationInSeconds * 1000);
        
        return Jwts.builder()
                .setHeaderParam("type", TOKEN_TYPE)
                .setSubject(user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles().stream().map(ERole::name).collect(Collectors.joining(", ")))
                .signWith(Keys.hmacShaKeyFor(this.secret.getBytes()), SignatureAlgorithm.HS512)
                .compact();
        
    }
    
    public String getIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(this.secret.getBytes())
                .build()
                .parseClaimsJws(token);
            
            return true;
        } catch (JwtException ex) {
            return false;
        }        
    }
    
}
