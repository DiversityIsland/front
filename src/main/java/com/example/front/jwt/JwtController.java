package com.example.front.jwt;

import com.example.front.controller.RestService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Collection;

import static com.example.front.jwt.CookieUtils.setCookie;
import static com.example.front.jwt.JwtUtils.generateAccessToken;
import static com.example.front.jwt.JwtUtils.generateRefreshToken;
import static com.example.front.jwt.JwtUtils.getRolesFromJson;


@Controller
@RequestMapping("/api")
@Slf4j
public class JwtController {


    @PostMapping("/auth")
    public void jwtAuthPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String credentials) {
        log.info("JwtController credentials: " + credentials);

        String usernameAuth = StringUtils.parse(credentials, "{\"username\":\"","\",");
        String jsonData = RestService.postJSON("http://localhost:8888/authentication", credentials).getBody();

        final JSONObject user = new JSONObject(jsonData);

        Integer id = (Integer) user.get("id");

        Collection<GrantedAuthority> roles = getRolesFromJson(user.getJSONArray("roles"));

        String accessToken = generateAccessToken(id.longValue(), usernameAuth, roles, 300);
        RefreshToken refreshToken = generateRefreshToken(id.longValue(), usernameAuth, 60*60*24*30);
        response.addCookie(setCookie("JWT", accessToken,300));
        response.addCookie(setCookie("JWR", refreshToken.getToken(),60*60*24*30));

        AuthUser.authUser(request, usernameAuth, roles);
    }

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