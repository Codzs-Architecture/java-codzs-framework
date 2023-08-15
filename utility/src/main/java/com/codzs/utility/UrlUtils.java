package com.codzs.utility;

public class UrlUtils {
    public static String toUrlEncode(String s) {
        String result = null;
        if (s != null) {
            result = java.net.URLEncoder.encode(s);
        }
        return result;
    }

    public static String toUrlDecode(String s) {
        String result = null;
        if (s != null) {
            result = java.net.URLDecoder.decode(s);
        }
        return result;
    }
}
