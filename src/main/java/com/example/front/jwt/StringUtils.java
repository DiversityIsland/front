package com.example.front.jwt;

public class StringUtils {
    static String parse(String text, String from, String to) {
        int linkOffset = text.indexOf(from, 0);
        if (linkOffset<0) return "";
        int linkOffsetEnd = text.indexOf(to, Math.max(linkOffset,0));
        if (linkOffsetEnd<0) linkOffsetEnd = text.length();
        return (linkOffset>=0)&&(linkOffset+from.length()<linkOffsetEnd) ? text.substring(linkOffset + from.length(), linkOffsetEnd) : "";
    };
}
