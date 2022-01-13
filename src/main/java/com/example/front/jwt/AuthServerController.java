package com.example.front.jwt;

import com.example.front.controller.RestService;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class AuthServerController {
    @PersistenceContext
    protected EntityManager entityManager;

    /*
     * используется при авторизации через "Вход"
     * http://localhost/api/authserver?username=ADMIN&password=ADMIN
     * контракт: (username, password -> id, username, roles, error)
     * */
    //Jackson ObjectMapper  reaDVALUE

    @GetMapping("/authserver")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username,
                          @RequestParam("password") String password) {

        String url = "http://localhost:8888/authserver?username="+username+"&password="+password;
        System.out.println("url string is: " + url);
        String test = RestService.getJSON(url).getBody();

        JSONObject obj = new JSONObject(test);
        int id = obj.getInt("id");
        String uname = obj.getString("username");
        Object urole = obj.getJSONArray("roles").get(0);
        System.out.println("JSON ROLES: " + urole);
        JSONObject obj2 = new JSONObject(urole.toString());
        String role = obj2.getString("name");

        System.out.println( "JSON id: " + id + " JSON name: " + uname + " JSON role: " + role);
        System.out.println("AuthServerController");
        return "{\"id\":\"" + id + "\",\"username\":\"" + uname + "\",\"roles\":[\"ROLE_" + role + "\"],\"error\":\"\"}";

        //todo заглушка на сервер авторизации
        //return "{\"id\":\"1\",\"username\":\"ADMIN\",\"roles\":[\"ROLE_ADMIN\",\"ROLE_USER\"],\"error\":\"\"}";

    }

}