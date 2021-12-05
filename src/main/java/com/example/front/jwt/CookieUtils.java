package com.example.front.jwt;

import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookieUtils {

    private CookieUtils() {
    }

    static Cookie setCookie(String name, String value, int age) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(age);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        return cookie;
    }

    public static String getCookieFromRequest(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
}
