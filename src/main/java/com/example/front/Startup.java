package com.example.front;

import com.example.front.jwt.JwtUtils;
import com.example.front.jwt.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class Startup {

    @Bean
    public void init() {
        Clog.status.log("Startup initializing");
      //  String result = JwtUtils.generateAccessToken(1L, "USER", JwtUtils.getRolesFromString("ROLE_ADMIN","ROLE_USER"), 3600);
      //  System.out.println("Token testL "+result);
        //TODO JWT Controller 39
    }

}