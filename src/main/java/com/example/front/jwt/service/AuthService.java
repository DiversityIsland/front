package com.example.front.jwt.service;

import com.example.front.jwt.domain.JwtAuthentication;
import io.jsonwebtoken.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthService {

    private final String jwtSecret;

    public AuthService(@Value("${jwt.secret.access}") String secret) {
        this.jwtSecret = secret;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            System.out.println(token);
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public Claims getClaims(@NonNull String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public JwtAuthentication getAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

}
