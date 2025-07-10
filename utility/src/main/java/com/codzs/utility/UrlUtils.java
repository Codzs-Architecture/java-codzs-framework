package com.codzs.utility;

import java.nio.charset.StandardCharsets;

public class UrlUtils {
    public static String toUrlEncode(String s) {
        String result = null;
        if (s != null) {
            result = java.net.URLEncoder.encode(s, StandardCharsets.UTF_8);
        }
        return result;
    }

    public static String toUrlDecode(String s) {
        String result = null;
        if (s != null) {
            result = java.net.URLDecoder.decode(s, StandardCharsets.UTF_8);
        }
        return result;
    }
}
