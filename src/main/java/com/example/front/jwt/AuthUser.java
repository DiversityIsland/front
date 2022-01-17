package com.example.front.jwt;

import com.example.front.Clog;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public class AuthUser {

    private AuthUser() {
    }

    public static void authUser(HttpServletRequest request, String username, Collection<? extends GrantedAuthority> roles) {
        System.out.println("AuthUser");
        Clog.status.log("authUser " + roles);
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(new UserPrincipal(username), null, roles);
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
}
