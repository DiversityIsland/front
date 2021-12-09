package com.example.front.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GatewayControllerTest {

        @Autowired
        private MockMvc mockMvc;


        @Test
        void userPage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk());
        }

        @Test
        void adminPage() throws Exception {
                this.mockMvc.perform(get("/admin"))
                        //.andDo(print())
                        .andExpect(status().is(401));

                this.mockMvc.perform(get("/admin/"))
                        //.andDo(print())
                        .andExpect(status().is(401));
        }


        @Test
        void feedbackPage() throws Exception {
                this.mockMvc.perform(get("/feedback"))
                        //.andDo(print())
                        .andExpect(status().is(401));

                this.mockMvc.perform(get("/feedback/"))
                        //.andDo(print())
                        .andExpect(status().is(401));
        }
}