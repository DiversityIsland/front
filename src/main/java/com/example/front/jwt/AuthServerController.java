package com.example.front.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;


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
    public String jwtAuth(HttpServletResponse response, HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {

        System.out.println("AuthServerController");
        String id = "", uname = "";
        String[] role = new String[4];

        try {
            String url = "jdbc:mysql://localhost:3306/platform";
            Connection conn;
            conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT platform.user.id, platform.user.username, platform.role.role_name " +
                    "FROM (platform.user_role INNER JOIN platform.role " +
                    "ON platform.user_role.role_id = platform.role.id) " +
                    "INNER JOIN platform.user ON platform.user_role.user_id = platform.user.id " +
                    "WHERE platform.user.username like \"" + username + "\"");
            int i = 0;
            while (rs.next()) {
                id = rs.getString(1);
                uname = rs.getString(2);
                role[i] = rs.getString(3);
                i++;
            }
            conn.close();
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.err.println("SQL script got an exception! ");
            System.err.println(e.getMessage());
        }

        String finalRole = "";
        for (int i = 0; i<role.length; i++){
            finalRole = finalRole + "\"ROLE_" + role[i] + "\",";
        }
        finalRole = finalRole.substring (0, finalRole.length() - 1);

        return "{\"id\":\"" + id + "\",\"username\":\"" + uname + "\",\"roles\":[" + finalRole + "],\"error\":\"\"}";

        //todo заглушка на сервер авторизации
       // return "{\"id\":\"1\",\"username\":\"ADMIN\",\"roles\":[\"ROLE_ADMIN\",\"ROLE_USER\"],\"error\":\"\"}";

    }

}