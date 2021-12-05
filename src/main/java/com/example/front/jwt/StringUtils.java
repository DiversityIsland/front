package com.example.front.jwt;

public class StringUtils {

    private StringUtils() {
    }

    public static String parse(String text, String from, String to) {
        int fromOffsetInText = text.indexOf(from, 0);
        if (fromOffsetInText < 0) return "";
        int parseOffset = fromOffsetInText + from.length();

        int toOffset = text.indexOf(to, parseOffset);
        if (toOffset < 0) return "";

        return parseOffset < toOffset ? text.substring(parseOffset, toOffset) : "";
    }

    public static String parseFrom(String text, String from) {
        int fromOffsetInText = text.indexOf(from, 0);
        if (fromOffsetInText < 0) return "";
        int parseOffset = fromOffsetInText + from.length();

        int toOffset = text.length();
        return parseOffset < toOffset ? text.substring(parseOffset, toOffset) : "";
    }

    public static String parseTo(String text, String to) {
        int parseOffset = 0;

        int toOffset = text.indexOf(to, parseOffset);
        if (toOffset < 0) return "";
        return parseOffset < toOffset ? text.substring(parseOffset, toOffset) : "";
    }



}
