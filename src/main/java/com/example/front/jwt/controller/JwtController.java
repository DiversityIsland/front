package com.example.front.jwt.controller;

import com.example.front.controller.RestService;
import com.example.front.jwt.service.CookieUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class JwtController {

        @PostMapping("/auth")
    public void jwtAuthPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String credentials) {
        log.info("JwtController credentials: " + credentials);

        final JSONObject responseData = new JSONObject(RestService.postJSON("http://localhost:8888/api/auth/login", credentials).getBody());
        final String accessToken = (String)responseData.get("accessToken");
        final String refreshToken = (String)responseData.get("refreshToken");
        response.addCookie(CookieUtils.setCookie("bearer", accessToken,300));
        response.addCookie(CookieUtils.setCookie("refresh", refreshToken,60*60*24*30));
    }

    @PostMapping("/token")
    public String jwtTokenPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String refreshToken) {
        log.info("JwtController refreshToken: " + refreshToken);
        final JSONObject responseData = new JSONObject(RestService.postJSON("http://localhost:8888/api/auth/token",
                refreshToken).getBody());
        final String accessToken = (String)responseData.get("accessToken");
        response.addCookie(CookieUtils.setCookie("bearer", accessToken,300));
        return accessToken;
    }

    @GetMapping("/logout")
    public String jwtLogout(HttpServletResponse response, Principal pr){
        String username = pr.getName();
        if (username!=null) {
            response.addCookie(CookieUtils.setCookie("bearer", null,0));
            response.addCookie(CookieUtils.setCookie("refresh", null,0));
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

}
