package com.example.front.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static com.example.front.jwt.CookieUtils.*;
import static com.example.front.jwt.JwtUtils.*;
import static com.example.front.jwt.JwtUtils.JwtTokenStatus.*;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private String updateJwtToken(String jwr) {
        JwtTokenStatus tokenRefreshStatus = checkToken(jwr);
        System.out.printf("Token status: %s\n", tokenRefreshStatus);

        if (tokenRefreshStatus == TOKEN_VALID) {
            Long uid = getUserIdFromJwt(jwr);
            String username = getFieldFromJwt(jwr, "username");
            return generateAccessToken(uid, username, 86400);
        } else {
            System.out.printf("Update JWT failed: JWR is %s\n", tokenRefreshStatus);
        }
        return "";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //skip auth filter if user is authorized
        if ((SecurityContextHolder.getContext().getAuthentication() != null) && (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())) {
            filterChain.doFilter(request, response);
            System.out.printf("[Session] %s: %s\n", request.getRequestedSessionId(), request.getRequestURL());
            return;
        }

        int remotePort = request.getRemotePort();
        //skip auth if user has no JWT token
        String jwt = getCookieFromRequest(request, "JWT");
        String jwr = getCookieFromRequest(request, "JWR");
        if ((jwt == null) && (jwr == null)) {
            System.out.printf("[%s] No JWT token\n", remotePort);
            filterChain.doFilter(request, response);
            return;
        }

        System.out.printf("[Filter] %s %s [%s<-%s:%s] User %s, session: %s\n",
                request.getMethod(), request.getRequestURL(), request.getLocalPort(), request.getRemoteHost(), request.getRemotePort(), request.getRemoteUser(), request.getRequestedSessionId());

        if (jwt == null) {
            jwt = updateJwtToken(jwr);
        }

        JwtTokenStatus tokenStatus = checkToken(jwt);
        System.out.printf("[%s] Token status: %s\n", remotePort, tokenStatus);

        if (tokenStatus == TOKEN_EXPIRED) {
            jwt = updateJwtToken(jwr);
            tokenStatus = checkToken(jwt);
        }

        if (tokenStatus == TOKEN_VALID) {
            String username = getFieldFromJwt(jwt,"username");
            String[] roles = getFieldFromJwt(jwt,"roles").split(" ");
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                authorities.add(() -> role);
            }

            AuthUser.authUser(request, username, authorities);
        }
        filterChain.doFilter(request, response);
    }

}
