package com.example.front.jwt;

import com.example.front.controller.RestService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.*;

import static com.example.front.jwt.CookieUtils.*;
import static com.example.front.jwt.JwtUtils.*;


@Controller
@RequestMapping("/api")
public class JwtController {


    @PostMapping("/auth")
    public void jwtAuthPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String credentials)
    {
        System.out.println("JwtController credentilas: " + credentials);
        //todo проверить корректность при неверном credentials
        String usernameAuth = StringUtils.parse(credentials, "username=","&");
        int x = credentials.indexOf("password=");
        String passwordAuth = credentials.substring(x+9);
        String jsonData = RestService.getJSON("http://localhost/api/authserver?username="+usernameAuth+"&password="+passwordAuth).getBody();

        //todo корректно ли преобразование
        final JSONObject user = new JSONObject(jsonData);

        Long id = Long.parseLong(user.getString("id"));
        String username = user.getString("username");
        Collection<GrantedAuthority> roles = getRolesFromJson(user.getJSONArray("roles"));

        //TODO !!! remove hardcoded roles
        String accessToken = generateAccessToken(id, usernameAuth, roles, 300);
        RefreshToken refreshToken = generateRefreshToken(id, usernameAuth, 60*60*24*30);
        response.addCookie(setCookie("JWT", accessToken,300));
        response.addCookie(setCookie("JWR", refreshToken.getToken(),60*60*24*30));

        AuthUser.authUser(request, usernameAuth, roles);
    }

    //http://localhost/api/logout
    @GetMapping("/logout")
    public String jwtLogout(HttpServletResponse response, Principal pr)
    {
        String username = pr.getName();
        if (username!=null) {
            response.addCookie(setCookie("JWT", "",0));
            response.addCookie(setCookie("JWR", "",0));
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

}