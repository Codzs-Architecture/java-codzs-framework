package com.codzs.utility;

import com.codzs.utility.UrlUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UrlUtilsTest {
    @Test
    public void testToUrlEncode_forNullString() {
        String s = null;
        String result = UrlUtils.toUrlEncode(s);
        assertEquals(s, result);
    }

    @Test
    public void testToUrlEncode_forEmptyString() {
        String s = "";
        String result = UrlUtils.toUrlEncode(s);
        assertEquals(s, result);
    }

    @Test
    public void testToUrlEncode_forValidString() {
        String s = "a";
        String result = UrlUtils.toUrlEncode(s);
        assertEquals(s, result);
    }

    @Test
    public void testToUrlDecode_forNullString() {
        String s = null;
        String result = UrlUtils.toUrlDecode(s);
        assertEquals(s, result);
    }

    @Test
    public void testToUrlDecode_forEmptyString() {
        String s = "";
        String result = UrlUtils.toUrlDecode(s);
        assertEquals(s, result);
    }

    @Test
    public void testToUrlDecode_forValidString() {
        String s = "a";
        String result = UrlUtils.toUrlDecode(s);
        assertEquals(s, result);
    }
}
