package com.example.front.jwt;

import com.example.front.Clog;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.example.front.jwt.CookieUtils.*;
import static com.example.front.jwt.JwtUtils.*;
import static com.example.front.jwt.JwtUtils.JwtTokenStatus.*;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private String updateJwtToken(String jwr) {
        JwtTokenStatus tokenRefreshStatus = checkToken(jwr);

        Clog.status_minor.log(String.format("Token status: %s", tokenRefreshStatus));

        if (tokenRefreshStatus == TOKEN_VALID) {
            Long uid = getUserIdFromJwt(jwr);
            String username = getFieldFromJwt(jwr, "username");
            Collection<GrantedAuthority> roles = getRolesFromJwt(jwr);
            return generateAccessToken(uid, username, roles, 86400);
        } else {
            Clog.status_minor.log(String.format("Update JWT failed: JWR is %s", tokenRefreshStatus));
        }
        return "";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //skip auth filter if user is authorized
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
                Clog.status_minor.log(String.format("[Session present] %s", request.getRequestURL()));
                filterChain.doFilter(request, response);
                return;
            } else {
                Clog.status_minor.log(String.format("[No session] %s", request.getRequestURL()));
            }
        } else {
            Clog.status.log("[Filter] SecurityContextHolder.getContext().getAuthentication() is NULL");
        }

        int remotePort = request.getRemotePort();
        //skip auth if user has no JWT token
        String jwt = getCookieFromRequest(request, "JWT");
        String jwr = getCookieFromRequest(request, "JWR");
        if ((jwt == null) && (jwr == null)) {
            Clog.status_minor.log(String.format("[%s] No JWT token", remotePort));
            filterChain.doFilter(request, response);
            return;
        }

        Clog.status_minor.log(String.format("[Filter] %s %s [%s<-%s:%s] User %s",
                request.getMethod(), request.getRequestURL(), request.getLocalPort(), request.getRemoteHost(), request.getRemotePort(), request.getRemoteUser()));

        if (jwt == null) {
            jwt = updateJwtToken(jwr);
        }

        JwtTokenStatus tokenStatus = checkToken(jwt);
        Clog.status_minor.log(String.format("[%s] Token status: %s", remotePort, tokenStatus));

        if (tokenStatus == TOKEN_EXPIRED) {
            jwt = updateJwtToken(jwr);
            tokenStatus = checkToken(jwt);
        }

        if (tokenStatus == TOKEN_VALID) {
            String username = getFieldFromJwt(jwt,"username");
            Collection<GrantedAuthority> roles = getRolesFromJwt(jwt);
            AuthUser.authUser(request, username, roles);

        }
        filterChain.doFilter(request, response);
    }

}
