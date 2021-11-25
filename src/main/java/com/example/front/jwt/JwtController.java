package com.example.front.jwt;

import com.example.front.controller.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static com.example.front.jwt.CookieUtils.*;
import static com.example.front.jwt.JwtUtils.*;


@Controller
@RequestMapping("/api")
public class JwtController {
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public JwtController(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }


    //http://localhost/api/auth?username=ADMIN&password=ADMIN
    @GetMapping("/auth")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {
        //todo проверить входящие параметры username и password на корректность
        String userStr = RestService.getJSON("http://localhost/api/authserver?username="+username+"&"+"password="+password).getBody();
      //  User user = service.getByUsername(username);

       // if ( (user!=null) && (user.isPasswordCorrect(password)) )
        {
//            if (!user.isEnabled()) {
//                return "redirect:/login?error=AccountDisabled";
//            }
//            if (!user.isAccountNonExpired()) {
//                return "redirect:/login?error=AccountExpired";
//            }
//            if (!user.isAccountNonLocked()) {
//                return "redirect:/login?error=AccountLocked";
//            }
//            if (!user.isCredentialsNonExpired()) {
//                return "redirect:/login?error=CredentialsExpired";
//            }

            //set JWT and JWT refresh tokens as cookies
            Long id = 1L;
            String accessToken = generateAccessToken(id, username, 300);
            RefreshToken refreshToken = generateRefreshToken(id, username, 60*60*24*30);
            response.addCookie(setCookie("JWT", accessToken,300));
            response.addCookie(setCookie("JWR", refreshToken.getToken(),60*60*24*30));

            //save JWT refresh token in DB (delete previous one if needed)
            refreshTokenRepository.deleteByUserName(username);
            refreshTokenRepository.save(refreshToken);


            User user = new User("ADMIN", "ADMIN", "Са ша", "Moiseev", "36", "admin@mail.ru",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()), new UserRole("ROLE_ADMIN"));

            //authorize User
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);

            return "redirect:/index";
       // } else {
          //  return "redirect:/login?error=WrongCredentials";
        }
    }

    //http://localhost/api/auth?username=ADMIN&password=ADMIN
    @PostMapping("/auth")
    public String jwtAuthPost(HttpServletResponse response, HttpServletRequest request, @RequestBody String[] credentials)
    {
        System.out.println(Arrays.toString(credentials));

        return "ok";
    }

    //http://localhost/api/logout
    @GetMapping("/logout")
    public String jwtLogout(HttpServletResponse response, Principal pr)
    {
        String username = pr.getName();
        if (username!=null) {
            refreshTokenRepository.deleteByUserName(username);
            response.addCookie(setCookie("JWT", "",0));
            response.addCookie(setCookie("JWR", "",0));
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login";
    }

}