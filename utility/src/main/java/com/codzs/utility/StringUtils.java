package com.codzs.utility;

import java.util.Base64;

public class StringUtils {
    public static String toReverse(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return new StringBuilder(text).reverse().toString();
    }

    public static String toLower(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.toLowerCase();
    }

    public static String toUpper(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.toUpperCase();
    }

    public static String toTitle(String text) {
        if (isEmpty(text)) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static String toCamelCase(String text) {
        if (isEmpty(text)) {
            return text;
        }

        String[] parts = text.toLowerCase().split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toTitle(part);
        }
        return camelCaseString;
    }

    public static String toSnakeCase(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceAll(" ", "_").toLowerCase();
    }

    public static String toTrim(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.trim();
    }

    public static String toTrimAll(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceAll("\\s+", "");
    }

    public static boolean toMatch(String text, String pattern) {
        if (isEmpty(text)) {
            return true;
        }
        return text.toLowerCase().matches(pattern);
    }

    public static boolean toMatchCaseSensitive(String text, String pattern) {
        if (isEmpty(text)) {
            return true;
        }
        return text.matches(pattern);
    }

    public static boolean isEmpty(String text) {
        return org.apache.commons.lang3.StringUtils.isEmpty(text);
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static boolean isBlank(String text) {
        return org.apache.commons.lang3.StringUtils.isBlank(text);
    }

    public static boolean isNotBlank(String text) {
        return !isBlank(text);
    }

    public static String toStatementCase(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceAll("([a-z])([A-Z])", "$1 $2").toLowerCase();
    }

    public static String filterEmoji(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return text.replaceAll("[^\\u0000-\\uFFFF]", "");
    }

    public static String toBase64Encode(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String toBase64Decode(String text) {
        if (isEmpty(text)) {
            return text;
        }
        return new String(Base64.getDecoder().decode(text));
    }
}
