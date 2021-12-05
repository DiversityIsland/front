package com.example.front.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void parse() {
        assertEquals("strin", StringUtils.parse("test string", "st ", "g"));
        assertEquals("est strin", StringUtils.parse("test string", "t", "g"));
        assertEquals("", StringUtils.parse("test string", "t", "\n"));
        assertEquals("", StringUtils.parse("test string", " ", "\n\n"));
        assertEquals("", StringUtils.parse("test string", "t", ""));
        assertEquals("test strin", StringUtils.parse("test string", "", "g"));
        assertEquals("", StringUtils.parse("test string", "", ""));
        assertEquals("strin", StringUtils.parse("test string", " ", "g"));
        assertEquals("", StringUtils.parse("test string", " ", " "));
        assertEquals("est", StringUtils.parse("test string", "t", " "));
        assertEquals("", StringUtils.parse("test string", "test string", "g"));
        assertEquals("", StringUtils.parse("test string", "test strin", "g"));
        assertEquals("n", StringUtils.parse("test string", "test stri", "g"));
        assertEquals("", StringUtils.parse("test string", "test string val", "g"));
        assertEquals("", StringUtils.parse("", "test", "g"));
        assertEquals("", StringUtils.parse("", "", "g"));
        assertEquals("", StringUtils.parse("", "g", ""));
        assertEquals("", StringUtils.parse("", "", ""));
        assertEquals("fG1_", StringUtils.parse("AbCdEfG1_2+3=4%5", "CdE", "2+3"));
        assertEquals("2+3=4", StringUtils.parse("AbCdEfG1_2+3=4%5", "1_", "%5"));
        assertEquals("", StringUtils.parse("AbCdEfG1_2+3=4%5", "1_", "%5\n"));
        assertEquals("1_2", StringUtils.parse("AbCdEfG1_2+3=4%5", "bCdEfG", "+"));
        assertEquals("%", StringUtils.parse("AbCdEfG1_2+3=4%5", "4", "5"));
        assertEquals("", StringUtils.parse("AbCdEfG1_2+3=4%5", "\n", "5"));
        assertEquals("", StringUtils.parse("AbCdEfG1_2+3=4%5", "Z", "4%5"));
    }

    @Test
    void parseFrom() {
        assertEquals("string", StringUtils.parseFrom("test string", "st "));
        assertEquals("est string", StringUtils.parseFrom("test string", "t"));
        assertEquals("string", StringUtils.parseFrom("test string", " "));
        assertEquals("test string", StringUtils.parseFrom("test string", ""));
        assertEquals("", StringUtils.parseFrom("test string", "test string"));
        assertEquals("g", StringUtils.parseFrom("test string", "test strin"));
        assertEquals("ng", StringUtils.parseFrom("test string", "test stri"));
        assertEquals("", StringUtils.parseFrom("test string", "test string val"));
        assertEquals(" string", StringUtils.parseFrom("test string", "test"));
        assertEquals("", StringUtils.parseFrom("", ""));
        assertEquals("", StringUtils.parseFrom("", "g"));
        assertEquals("fG1_2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "CdE"));
        assertEquals("2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "1_"));
        assertEquals("", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "1_\n"));
        assertEquals("1_2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "bCdEfG"));
        assertEquals("%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "4"));
        assertEquals("", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "5"));
        assertEquals("", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "\n"));
        assertEquals("", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "Z"));
    }

    @Test
    void parseTo() {
        assertEquals("te", StringUtils.parseTo("test string", "st "));
        assertEquals("test s", StringUtils.parseTo("test string", "tr"));
        assertEquals("test", StringUtils.parseTo("test string", " "));
        assertEquals("", StringUtils.parseTo("test string", ""));
        assertEquals("", StringUtils.parseTo("test string", "test string"));
        assertEquals("", StringUtils.parseTo("test string", "test strin"));
        assertEquals("t", StringUtils.parseTo("test string", "est stri"));
        assertEquals("", StringUtils.parseTo("test string", "test string val"));
        assertEquals("tes", StringUtils.parseTo("test string", "t "));
        assertEquals("", StringUtils.parseTo("", ""));
        assertEquals("test strin", StringUtils.parseTo("test string", "g"));
        assertEquals("Ab", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "CdE"));
        assertEquals("AbCdEfG", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "1_"));
        assertEquals("A", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "bCdEfG"));
        assertEquals("AbCdEfG1_2+3=", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "4"));
        assertEquals("AbCdEfG1_2+3=4%", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "5"));
        assertEquals("", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "5\n"));
        assertEquals("", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "\n"));
        assertEquals("", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "Z"));
    }


}