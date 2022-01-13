package com.example.front.jwt;

import com.example.front.controller.RestService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;


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

    @GetMapping("/authserver")
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username,
                          @RequestParam("password") String password) {

        String url = "http://localhost:8888/authserver?username="+username+"&password="+password;
        String jsonUrl = RestService.getJSON(url).getBody();

        JSONObject jsonObj = new JSONObject(jsonUrl);
        int id = jsonObj.getInt("id");
        String uname = jsonObj.getString("username");

        String rolesArr = String.valueOf(jsonObj.get("roles"));
        String roles = "";
        JSONArray jsonObj2 = new JSONArray(rolesArr);
        Iterator jsonObj2Itr = jsonObj2.iterator();
        while (jsonObj2Itr.hasNext()){
            JSONObject test3 = (JSONObject) jsonObj2Itr.next();
            roles = roles + "\"ROLE_" + test3.get("name") + "\"" + ",";
        }
        return "{\"id\":\"" + id + "\",\"username\":\"" + uname + "\",\"roles\":[" + roles + "],\"error\":\"\"}";

        //return "{\"id\":\"1\",\"username\":\"ADMIN\",\"roles\":[\"ROLE_ADMIN\",\"ROLE_USER\"],\"error\":\"\"}";

    }

}