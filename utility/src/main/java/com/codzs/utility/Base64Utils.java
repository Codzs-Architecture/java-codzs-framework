package com.codzs.utility;

public class Base64Utils {
    public static String encode(String str) {
        return java.util.Base64.getEncoder().encodeToString(str.getBytes());
    }

    // encode using bytes array
    public static String encode(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public static String decode(String str) {
        return new String(java.util.Base64.getDecoder().decode(str));
    }

    public static byte[] decodeToBytes(String str) {
        return java.util.Base64.getDecoder().decode(str);
    }
}
