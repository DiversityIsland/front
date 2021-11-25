package com.example.front.jwt;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.context.SecurityContextHolder;
        import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.security.Principal;
        import java.util.Date;

        import static com.example.front.jwt.CookieUtils.*;
        import static com.example.front.jwt.JwtUtils.*;


@RestController
@RequestMapping("/api")
public class AuthServerController {
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public AuthServerController( RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    //http://localhost/api/authserver?username=ADMIN&password=ADMIN
    @GetMapping("/authserver")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {
            //return "AccountDisabled";
            //return "AccountExpired";
            //return "AccountLocked";
            //return "CredentialsExpired";
            //return "WrongCredentials";

            return "{\"error\"=\"\", \"id\"=\"1\", \"isEnabled\"=\"true\", \"isAccountNonExpired\"=\"true\", \"isAccountNonLocked\"=\"true\", \"isCredentialsNonExpired\"=\"true\"}";
    }

    static User user = new User("ADMIN", "ADMIN", "Са ша", "Moiseev", "36", "admin@mail.ru",new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()), new UserRole("ROLE_ADMIN"));
    //http://localhost/api/getById?id=1
    @GetMapping("/getById")
    public static User getById(@RequestParam("id") Long id)
    {
        return user;
    }

}