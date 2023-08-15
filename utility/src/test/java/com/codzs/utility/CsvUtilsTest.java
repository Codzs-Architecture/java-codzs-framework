package com.codzs.utility;

import com.codzs.utility.CsvUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvUtilsTest {
    @Test
    public void testToCsv_forNullString() {
        String[] s = null;
        String result = CsvUtils.toCsv(s);
        assertEquals("", result);
    }

    @Test
    public void testToCsv_forEmptyString() {
        String[] s = new String[0];
        String result = CsvUtils.toCsv(s);
        assertEquals("", result);
    }

    @Test
    public void testToCsv_forValidString() {
        String[] s = new String[2];
        s[0] = "a";
        s[1] = "b";
        String result = CsvUtils.toCsv(s);
        assertEquals("a,b", result);
    }

    @Test
    public void testFromCsv_forNullString() {
        String s = null;
        String[] result = CsvUtils.fromCsv(s);
        assertEquals(0, result.length);
    }

    @Test
    public void testFromCsv_forEmptyString() {
        String s = "";
        String[] result = CsvUtils.fromCsv(s);
        assertEquals(1, result.length);
        assertEquals("", result[0]);
    }

    @Test
    public void testFromCsv_forValidString() {
        String s = "a,b";
        String[] result = CsvUtils.fromCsv(s);
        assertEquals(2, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
    }
}
