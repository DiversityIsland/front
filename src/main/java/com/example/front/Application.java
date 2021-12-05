package com.example.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import java.io.IOException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        openHomePage();
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    private static void openHomePage() {
        Runtime rt = Runtime.getRuntime();
        execRuntime(rt, "rundll32 url.dll, FileProtocolHandler http://localhost/");
    }

    private static Process execRuntime(Runtime rt, String command) {
        try {
            return rt.exec(command);
        } catch (IOException e) {
            Clog.err.log(String.format("execRuntime failed command %s", command));
        }
        return null;
    }
}
