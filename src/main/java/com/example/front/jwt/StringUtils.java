package com.example.front.jwt;


public class StringUtils {

    public static String parse(String text, String from, String to) {
        if ((from.length() == 0)||(to.length() == 0)) {
            return "";
        }

        int fromOffsetInText = text.indexOf(from);
        if (fromOffsetInText < 0) return "";
        int parseOffset = fromOffsetInText + from.length();

        int toOffset = text.indexOf(to, parseOffset);
        if (toOffset < 0) return "";

        return parseOffset < toOffset ? text.substring(parseOffset, toOffset) : "";
    }


    public static String parseFrom(String text, String from) {

        if (from.length() == 0) {
            return "";
        }

        int fromOffsetInText = text.indexOf(from);
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


    private int classFromOffset = 0;
    private String cutText = "";

    public String cut(String text, String from, String to) {
        if (!cutText.equals(text)) {
            cutText = text;
            classFromOffset = 0;
        }

        if ((from.length() == 0)||(to.length() == 0)) {
            return "";
        }

        int cutTextIndex = text.indexOf(from, classFromOffset);
        if (cutTextIndex < 0) return "";
        int fromOffset = cutTextIndex + from.length();

        int toOffset = text.indexOf(to, fromOffset);
        if (toOffset < 0) return "";

        if (fromOffset < toOffset) {
            classFromOffset = toOffset;
            return text.substring(fromOffset, toOffset);
        } else {
            return "";
        }
    }

    public String cutFrom(String text, String from) {
        if (!cutText.equals(text)) {
            cutText = text;
            classFromOffset = 0;
        }

        if (from.length() == 0) {
            return "";
        }

        int cutTextIndex = text.indexOf(from, classFromOffset);
        if (cutTextIndex < 0) return "";
        int fromOffset = cutTextIndex + from.length();

        int toOffset = text.length();

        if (fromOffset < toOffset) {
            classFromOffset = toOffset;
            return text.substring(fromOffset, toOffset);
        } else {
            return "";
        }
    }

    public String cutTo(String text, String to) {
        if (!cutText.equals(text)) {
            cutText = text;
            classFromOffset = 0;
        }

        if (to.length() == 0) {
            return "";
        }

        int fromOffset = classFromOffset;

        int toOffset = text.indexOf(to, fromOffset);
        if (toOffset < 0) return "";

        if (fromOffset < toOffset) {
            classFromOffset = toOffset;
            return text.substring(fromOffset, toOffset);
        } else {
            return "";
        }
    }

}
