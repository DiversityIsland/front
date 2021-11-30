package com.example.front.jwt;

        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AuthServerController {
    /*
    * используется при авторизации через "Вход"
    * http://localhost/api/authserver?username=ADMIN&password=ADMIN
    * контракт: (username, password -> id, username, roles, error)
    * */
    @GetMapping("/authserver")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password)
    {//Jackson ObjectMapper  reaDVALUE
            //todo заглушка на сервер авторизации
        //return user;
        return "{\"id\":\"1\",\"username\":\"ADMIN\",\"roles\":[\"ROLE_ADMIN\",\"ROLE_USER\"],\"error\":\"\"}";
    }

}