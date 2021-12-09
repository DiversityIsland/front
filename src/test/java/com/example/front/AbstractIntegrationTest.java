package com.example.front;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = Application.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {
}
