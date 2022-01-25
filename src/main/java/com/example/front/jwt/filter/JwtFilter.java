package com.example.front.jwt.filter;

import com.example.front.jwt.controller.JwtController;
import com.example.front.jwt.domain.JwtAuthentication;
import com.example.front.jwt.service.AuthService;
import com.example.front.jwt.service.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    private static final String Cookie = "cookie";
    private final AuthService authService;
    private  final JwtController jwtController;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        final String refreshToken = getRefreshTokenFromRequest((HttpServletRequest) servletRequest);
        if(token == null && refreshToken != null && authService.getAuthentication() == null ) {
            token = jwtController.jwtTokenPost((HttpServletResponse) servletResponse,
                    (HttpServletRequest)servletRequest,
                    "{\"refreshToken\":" + "\"" + refreshToken + "\"" +"}");
        }
        if (token != null && authService.validateToken(token)) {
            final Claims claims = authService.getClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(Cookie);
        String[] tokens = new String[2];
        if (bearer != null) {
            tokens = bearer.split(";");
            for (String str: tokens) {
                if (StringUtils.hasText(str) && str.trim().startsWith("bearer")) {
                    return str.trim().substring(7);
                }
            }
        }
        return null;
    }

    private String getRefreshTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(Cookie);
        String[] tokens = new String[2];
        if (bearer != null) {
            tokens = bearer.split(";");
            for (String str: tokens) {
                if (StringUtils.hasText(str) && str.trim().startsWith("refresh")) {
                    return str.trim().substring(8);
                }
            }
        }
        return null;
    }

}
