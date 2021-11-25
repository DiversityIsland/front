package com.example.front.jwt;

import com.example.front.controller.RestService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

    //http://localhost/api/auth?username=ADMIN&password=ADMIN
    @GetMapping("/auth")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {
        //set JWT and JWT refresh tokens as cookies
        String accessToken = generateAccessToken(1L, username, 300);
        RefreshToken refreshToken = generateRefreshToken(1L, username, 60*60*24*30);
        response.addCookie(setCookie("JWT", accessToken,300));
        response.addCookie(setCookie("JWR", refreshToken.getToken(),60*60*24*30));

        User user = new User("ADMIN", "ADMIN", "admin@mail.ru", "ROLE_ADMIN");

        //authorize User
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/index";

    }


    @PostMapping("/auth")
    public void jwtAuthPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String credentials)
    {
        //todo проверить корректность при неверном credentials
        String usernameAuth = StringUtils.parse(credentials, "username=","&");
        String passwordAuth = StringUtils.parse(credentials, "password=","\n");

        String JSON_DATA = RestService.getJSON("http://localhost/api/authserver?username="+usernameAuth+"&password="+passwordAuth).getBody();

        //todo корректно ли преобразование
        final JSONObject user = new JSONObject(JSON_DATA);
        Long id = Long.parseLong(user.getString("id"));
        String username = user.getString("username");

        String accessToken = generateAccessToken(id, username, 300);
        RefreshToken refreshToken = generateRefreshToken(id, username, 60*60*24*30);
        response.addCookie(setCookie("JWT", accessToken,300));
        response.addCookie(setCookie("JWR", refreshToken.getToken(),60*60*24*30));

        //todo try catch
        JSONArray rolesJson = user.getJSONArray("roles");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        for (Object role : rolesJson) {
            roles.add(role::toString);
        }

        AuthUser.authUser(request, username, roles);
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