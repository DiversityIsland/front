package com.example.front.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void parse() {
        assertEquals("strin",   StringUtils.parse("test string", "st ", "g"));
        assertEquals("est strin", StringUtils.parse("test string", "t", "g"));
        assertEquals("",        StringUtils.parse("test string", "t", "\n"));
        assertEquals("",        StringUtils.parse("test string", " ", "\n\n"));
        assertEquals("",        StringUtils.parse("test string", "t", ""));
        assertEquals("",        StringUtils.parse("test string", "", "g"));
        assertEquals("",        StringUtils.parse("test string", "", ""));
        assertEquals("strin",   StringUtils.parse("test string", " ", "g"));
        assertEquals("",        StringUtils.parse("test string", " ", " "));
        assertEquals("est",     StringUtils.parse("test string", "t", " "));
        assertEquals("",        StringUtils.parse("test string", "test string", "g"));
        assertEquals("",        StringUtils.parse("test string", "test strin", "g"));
        assertEquals("n",       StringUtils.parse("test string", "test stri", "g"));
        assertEquals("",        StringUtils.parse("test string", "test string val", "g"));
        assertEquals("",        StringUtils.parse("", "test", "g"));
        assertEquals("",        StringUtils.parse("", "", "g"));
        assertEquals("",        StringUtils.parse("", "g", ""));
        assertEquals("",        StringUtils.parse("", "", ""));
        assertEquals("fG1_",    StringUtils.parse("AbCdEfG1_2+3=4%5", "CdE", "2+3"));
        assertEquals("2+3=4",   StringUtils.parse("AbCdEfG1_2+3=4%5", "1_", "%5"));
        assertEquals("",        StringUtils.parse("AbCdEfG1_2+3=4%5", "1_", "%5\n"));
        assertEquals("1_2",     StringUtils.parse("AbCdEfG1_2+3=4%5", "bCdEfG", "+"));
        assertEquals("%",       StringUtils.parse("AbCdEfG1_2+3=4%5", "4", "5"));
        assertEquals("",        StringUtils.parse("AbCdEfG1_2+3=4%5", "\n", "5"));
        assertEquals("",        StringUtils.parse("AbCdEfG1_2+3=4%5", "Z", "4%5"));
    }

    @Test
    void parseFrom() {
        assertEquals("string",  StringUtils.parseFrom("test string", "st "));
        assertEquals("est string", StringUtils.parseFrom("test string", "t"));
        assertEquals("string",  StringUtils.parseFrom("test string", " "));
        assertEquals("",        StringUtils.parseFrom("test string", ""));
        assertEquals("",        StringUtils.parseFrom("test string", "test string"));
        assertEquals("g",       StringUtils.parseFrom("test string", "test strin"));
        assertEquals("ng",      StringUtils.parseFrom("test string", "test stri"));
        assertEquals("",        StringUtils.parseFrom("test string", "test string val"));
        assertEquals(" string", StringUtils.parseFrom("test string", "test"));
        assertEquals("",        StringUtils.parseFrom("", ""));
        assertEquals("",        StringUtils.parseFrom("", "g"));
        assertEquals("fG1_2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "CdE"));
        assertEquals("2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "1_"));
        assertEquals("",        StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "1_\n"));
        assertEquals("1_2+3=4%5", StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "bCdEfG"));
        assertEquals("%5",      StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "4"));
        assertEquals("",        StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "5"));
        assertEquals("",        StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "\n"));
        assertEquals("",        StringUtils.parseFrom("AbCdEfG1_2+3=4%5", "Z"));
    }

    @Test
    void parseTo() {
        assertEquals("te",      StringUtils.parseTo("test string", "st "));
        assertEquals("test s",  StringUtils.parseTo("test string", "tr"));
        assertEquals("test",    StringUtils.parseTo("test string", " "));
        assertEquals("",        StringUtils.parseTo("test string", ""));
        assertEquals("",        StringUtils.parseTo("test string", "test string"));
        assertEquals("",        StringUtils.parseTo("test string", "test strin"));
        assertEquals("t",       StringUtils.parseTo("test string", "est stri"));
        assertEquals("",        StringUtils.parseTo("test string", "test string val"));
        assertEquals("tes",     StringUtils.parseTo("test string", "t "));
        assertEquals("",        StringUtils.parseTo("", ""));
        assertEquals("test strin", StringUtils.parseTo("test string", "g"));
        assertEquals("Ab",      StringUtils.parseTo("AbCdEfG1_2+3=4%5", "CdE"));
        assertEquals("AbCdEfG", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "1_"));
        assertEquals("A",       StringUtils.parseTo("AbCdEfG1_2+3=4%5", "bCdEfG"));
        assertEquals("AbCdEfG1_2+3=", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "4"));
        assertEquals("AbCdEfG1_2+3=4%", StringUtils.parseTo("AbCdEfG1_2+3=4%5", "5"));
        assertEquals("",        StringUtils.parseTo("AbCdEfG1_2+3=4%5", "5\n"));
        assertEquals("",        StringUtils.parseTo("AbCdEfG1_2+3=4%5", "\n"));
        assertEquals("",        StringUtils.parseTo("AbCdEfG1_2+3=4%5", "Z"));
    }

    @Test
    void cut() {
        StringUtils parser = new StringUtils();
        assertEquals("",        parser.cut("12345", "\n","1"));
        assertEquals("",        parser.cut("12345", "1","\n"));
        assertEquals("",        parser.cut("12345", "1","2"));

        assertEquals("",        parser.cut("12345", "","1"));
        assertEquals("",        parser.cut("12345", "","2"));
        assertEquals("2",       parser.cut("12345", "1","3"));
        assertEquals("",        parser.cut("12345", "2","3"));

        assertEquals("",        parser.cut("_1_2_3_4_5_", "","_"));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "\n","_"));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "_",""));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "_","\n"));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "",""));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "\n","\n"));

        assertEquals("1",       parser.cut("_1_2_3_4_5_", "_","_"));
        assertEquals("2",       parser.cut("_1_2_3_4_5_", "_","_"));
        assertEquals("3",       parser.cut("_1_2_3_4_5_", "_","_"));
        assertEquals("4",       parser.cut("_1_2_3_4_5_", "_","_"));
        assertEquals("5",       parser.cut("_1_2_3_4_5_", "_","_"));
        assertEquals("",        parser.cut("_1_2_3_4_5_", "_","_"));

        assertEquals("1_2_3_4_", parser.cut("_1_2_3_4_5", "_","5"));

        assertEquals("_2_3",    parser.cut("1_2_3_45", "1","_4"));
        assertEquals("",        parser.cut("1_2_3_45", "_4","5"));
        assertEquals("4",       parser.cut("1_2_3_45", "_","5"));

        assertEquals("123",     parser.cut("_123_45_12_89", "_","_"));
        assertEquals("45",      parser.cut("_123_45_12_89", "_","_"));
    }

    @Test
    void cutFrom() {
        StringUtils parser = new StringUtils();
        assertEquals("",        parser.cutFrom("12345", "\n"));
        assertEquals("2345",    parser.cutFrom("12345", "1"));
        assertEquals("",        parser.cutFrom("12345", "1"));
        assertEquals("2345_",   parser.cutFrom("12345_", "1"));
        assertEquals("345",     parser.cutFrom("12345", "2"));
        assertEquals("",        parser.cutFrom("12345", "3"));
        assertEquals("",        parser.cutFrom("12345", "3"));
        assertEquals("456",     parser.cutFrom("123456", "3"));

        assertEquals("",        parser.cutFrom("_1_2_3_4_5_", ""));
        assertEquals("",        parser.cutFrom("_1_2_3_4_5_", "\n"));
        assertEquals("3_4_5_",  parser.cutFrom("_1_2_3_4_5_", "2_"));
        assertEquals("",        parser.cutFrom("_1_2_3_4_5_", "_"));
        assertEquals("",        parser.cutFrom("_1_2_3_4_5_", ""));
        assertEquals("",        parser.cutFrom("_1_2_3_4_5_", "\n"));

        assertEquals("_5",      parser.cutFrom("1_2_3_4_5", "2_3_4"));

        assertEquals("",        parser.cutFrom("1_2_3_45", "45"));
        assertEquals("5",       parser.cutFrom("1_2_3_45", "4"));
        assertEquals("",        parser.cutFrom("1_2_3_45", "_"));

        assertEquals("2345",    parser.cutFrom("12345", "1"));
        assertEquals("",        parser.cutFrom("12345", "1"));
        assertEquals("",        parser.cutFrom("12345", "4"));
        assertEquals("345_",    parser.cutFrom("12345_", "2"));
        assertEquals("",        parser.cutFrom("12345_", "3"));
        assertEquals("",        parser.cutFrom("12345_", "5"));
        assertEquals("5",       parser.cutFrom("12345", "1234"));
    }

    @Test
    void cutTo() {
        StringUtils parser = new StringUtils();
        assertEquals("",        parser.cutTo("12345", "\n"));
        assertEquals("",        parser.cutTo("12345", "1"));
        assertEquals("12",      parser.cutTo("12345", "3"));
        assertEquals("12345",   parser.cutTo("12345_", "_"));
        assertEquals("1",       parser.cutTo("12345", "2"));
        assertEquals("2",       parser.cutTo("12345", "3"));
        assertEquals("",        parser.cutTo("12345", "3"));
        assertEquals("12",      parser.cutTo("123456", "3"));

        assertEquals("",        parser.cutTo("_1_2_3_4_5_", ""));
        assertEquals("",        parser.cutTo("_1_2_3_4_5_", "\n"));
        assertEquals("",        parser.cutTo("_1_2_3_4_5_", "_"));
        assertEquals("_1",      parser.cutTo("_1_2_3_4_5_", "_2"));
        assertEquals("",        parser.cutTo("_1_2_3_4_5_", ""));
        assertEquals("_2_3_4_", parser.cutTo("_1_2_3_4_5_", "5"));

        assertEquals("_1_2_3",  parser.cutTo("_1_2_3_4_5", "_4"));
        assertEquals("",        parser.cutTo("_1_2_3_4_5", "_"));

        assertEquals("_1",      parser.cutTo("_1_2_3_4_5_", "_2"));

        assertEquals("_",       parser.cutTo("_1_2_3_4_5", "1_2_3_4_"));
        assertEquals("1_",      parser.cutTo("_1_2_3_4_5", "2_3_4"));

        assertEquals("1",       parser.cutTo("12345", "2"));
        assertEquals("2",       parser.cutTo("12345", "3"));
        assertEquals("34",      parser.cutTo("12345", "5"));

        assertEquals("12",      parser.cutTo("12345_", "34"));
        assertEquals("3",       parser.cutTo("12345_", "4"));
        assertEquals("4",       parser.cutTo("12345_", "5"));

    }

    @Test
    void allMethods() {
        StringUtils parser = new StringUtils();
        String s1 = StringUtils.parseTo("123456", "34");
        String s2 = StringUtils.parse("123456", "12", "56");
        String s3 = StringUtils.parseFrom("123456", "34");
        String s4 = parser.cutFrom(s1 + s2 + s3, "12");
        String s5 = parser.cut(s4, "3","6");
        String s6 = parser.cutTo(s5, "5");

        assertEquals("4", s6);

        s1 = StringUtils.parseTo("abcdefg", "c");
        s2 = StringUtils.parse("abcdefg", "b", "e");
        s3 = StringUtils.parseFrom("abcdefg", "d");
        s4 = parser.cutFrom(s1 + s2 + s3, "bc");
        s5 = parser.cut(s4, "d","g");
        s6 = parser.cutTo(s5, "f");

        assertEquals("e", s6);
    }
}