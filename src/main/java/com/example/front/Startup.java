package com.example.front;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Startup {

    @Bean
    public void init() {
        System.out.println("Startup initializing");
    }
}