package com.example.front.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestServiceTest {

    @Test
    void getJSON() {
        assertEquals("{\"testPassed\":\"true\"}", RestService.getJSON("http://localhost/test").getBody());
    }

    @Test
    void postJSON() {
        assertEquals("testPassed", RestService.postJSON("http://localhost/test", "testPassed").getBody());
        assertEquals("random123", RestService.postJSON("http://localhost/test", "random123").getBody());
    }
}