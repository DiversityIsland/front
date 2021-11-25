package com.example.front.jwt;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import java.util.Date;



@RestController
@RequestMapping("/api")
public class AuthServerController {

    //http://localhost/api/authserver?username=ADMIN&password=ADMIN
    @GetMapping("/authserver")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {
            //return "AccountDisabled";
            //return "AccountExpired";
            //return "AccountLocked";
            //return "CredentialsExpired";
            //return "WrongCredentials";
            //todo заглушка на сервер авторизации
            return "{\"error\":\"\",\"id\":\"1\",\"username\":\"ADMIN\",\"roles\":[\"ROLE_ADMIN\",\"ROLE_USER\"],\"isEnabled\":\"true\",\"isAccountNonExpired\":\"true\",\"isAccountNonLocked\":\"true\",\"isCredentialsNonExpired\":\"true\"}";
    }

    static User user = new User("ADMIN", "ADMIN",  "admin@mail.ru", "ROLE_ADMIN");
    //http://localhost/api/getById?id=1
    @GetMapping("/getById")
    public static User getById(@RequestParam("id") Long id)
    {
        //todo заглушка на сервер авторизации
        return user;
    }

}