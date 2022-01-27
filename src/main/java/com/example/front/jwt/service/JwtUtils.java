package com.example.front.jwt.service;

import com.example.front.jwt.domain.JwtAuthentication;
import com.example.front.jwt.domain.Role;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        return (Set<Role>) claims.get("roles", List.class).stream()
                .map(el -> new Gson().fromJson(el.toString(), HashMap.class).get("authority"))
                .map(el -> Role.valueOf(el.toString()))
                .collect(Collectors.toSet());
    }

}
