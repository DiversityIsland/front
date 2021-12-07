package com.example.front.jwt;

import com.example.front.Clog;
import io.jsonwebtoken.*;
import org.json.JSONArray;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtUtils {
    private static final String JWT_SECRET = "your-256-bit-secret";

    enum JwtTokenStatus {
        TOKEN_VALID,
        TOKEN_EXPIRED,
        TOKEN_MALFORMED,
        TOKEN_BAD_SIGNATURE,
        TOKEN_ILLEGAL,
        TOKEN_UNSUPPORTED
    }

    public static String generateAccessToken(Long id, String username, Collection<GrantedAuthority> roles, int maxAgeInSec) {
        Date now = new Date(System.currentTimeMillis());
        Date expiry = new Date(now.getTime() + maxAgeInSec * 1000L);
        return Jwts.builder()
                .setSubject(id.toString())
                .claim("username", username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setIssuer("token-issuer")
                .setAudience("partner_id")
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public static RefreshToken generateRefreshToken(Long id, String username, int maxAgeInSec) {
        Date now = new Date(System.currentTimeMillis());
        Date expiry = new Date(now.getTime() + maxAgeInSec * 1000L);

        String token = Jwts.builder()
                .setSubject(id.toString())
                .claim("username", username)
                .claim("roles", getRolesFromString("ROLE_ADMIN","ROLE_USER"))
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
        return new RefreshToken(token, id.toString(), username, expiry);
    }

    public static Long getUserIdFromJwt(String token) {
        Claims claim = Jwts.parser().setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        String id;
        try {
            id = claim.getSubject();
        } catch (Exception e) {
            Clog.status_minor.log("[Filter] JWT token has no subject");
            return 0L;
        }
        return Long.parseLong(id);
    }

    public static String getFieldFromJwt(String token, String field) {
        Claims claim = Jwts.parser().setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        String username;
        try {
            username = claim.get(field).toString();
        } catch (Exception e) {
            Clog.status_minor.log(String.format("[Filter] JWT token has no %s", field));
            return "";
        }
        return username;
    }

    public static JwtTokenStatus checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
            Clog.status_minor.log(claims.toString());
            long expiresIn = (claims.getExpiration().getTime() - System.currentTimeMillis()) / 1000;
            Clog.status.log(String.format("[token] expires in %ss %s", expiresIn, token));
            return JwtTokenStatus.TOKEN_VALID;
        } catch (ExpiredJwtException e) {
            Clog.status.log("[Filter] JWT token expired");
            return JwtTokenStatus.TOKEN_EXPIRED;
        } catch (MalformedJwtException e) {
            Clog.status.log("[Filter] JWT token malformed");
            return JwtTokenStatus.TOKEN_MALFORMED;
        } catch (SignatureException e) {
            Clog.status.log("[Filter] JWT token - bad signature");
            return JwtTokenStatus.TOKEN_BAD_SIGNATURE;
        } catch (IllegalArgumentException e) {
            Clog.status.log("[Filter] JWT token - illegal argument");
            return JwtTokenStatus.TOKEN_ILLEGAL;
        } catch (UnsupportedJwtException e) {
            Clog.status.log("[Filter] JWT token unsupported");
            return JwtTokenStatus.TOKEN_UNSUPPORTED;
        }
    }


    public static Collection<GrantedAuthority> getRolesFromJson (JSONArray rolesJson) {
        Collection<GrantedAuthority> roles = new ArrayList<>();
        for (Object role : rolesJson) {
            roles.add(role::toString);
        }
        Clog.warning.log("getRolesFromJson: " + roles);
        return roles;
    }

    private static Collection<GrantedAuthority> getRolesFromString (String... roles) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        for (Object role : roles) {
            result.add(role::toString);
        }
        Clog.warning.log("getRolesFromString: " + result);
        return result;
    }

    public static Collection<GrantedAuthority> getRolesFromJwt (String token) {
        Claims claim = Jwts.parser().setSigningKey(JWT_SECRET.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        String strResult;

        try {
            strResult = claim.get("roles").toString();
        } catch (Exception e) {
            Clog.status_minor.log("[Filter] JWT token has no roles");
            return Collections.emptyList();
        }

        Collection<GrantedAuthority> result = new ArrayList<>();
        StringUtils parser = new StringUtils();
        String authority;
        while (!Objects.equals(authority = parser.cut(strResult, "authority=", "}"), ""))
        {
            result.add(authority::toString);
        }

        Clog.status.log("getRolesFromJwt: " + result);
        return result;
    }

}
