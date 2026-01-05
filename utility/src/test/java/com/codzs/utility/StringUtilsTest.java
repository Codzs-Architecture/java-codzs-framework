package com.codzs.utility;

import com.codzs.utility.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    public void testToReverse_forNullString() {
        assertNull(null, StringUtils.toReverse(null));
    }

    @Test
    public void testToReverse_forEmptyString() {
        assertEquals("", StringUtils.toReverse(""));
    }

    @Test
    public void testToReverse_forValidString() {
        assertEquals("olleH", StringUtils.toReverse("Hello"));
        assertEquals("dlroW olleH", StringUtils.toReverse("Hello World"));
        assertEquals("dlroW, olleH", StringUtils.toReverse("Hello ,World"));
    }

    @Test
    public void testToLower_forNullString() {
        assertNull(StringUtils.toLower(null));
    }

    @Test
    public void testToLower_forEmptyString() {
        assertEquals("", StringUtils.toLower(""));
    }

    @Test
    public void testToLower_forValidString() {
        assertEquals("hello", StringUtils.toLower("Hello"));
        assertEquals("hello world", StringUtils.toLower("Hello World"));
        assertEquals("hello ,world", StringUtils.toLower("Hello ,World"));
    }

    @Test
    public void testToUpper_forNullString() {
        assertNull(StringUtils.toUpper(null));
    }

    @Test
    public void testToUpper_forEmptyString() {
        assertEquals("", StringUtils.toUpper(""));
    }

    @Test
    public void testToUpper_forValidString() {
        assertEquals("HELLO", StringUtils.toUpper("Hello"));
        assertEquals("HELLO WORLD", StringUtils.toUpper("Hello World"));
        assertEquals("HELLO ,WORLD", StringUtils.toUpper("Hello ,World"));
    }

    @Test
    public void testToTitle_forNullString() {
        assertNull(StringUtils.toTitle(null));
    }

    @Test
    public void testToTitle_forEmptyString() {
        assertEquals("", StringUtils.toTitle(""));
    }

    @Test
    public void testToTitle_forValidString() {
        assertEquals("Hello", StringUtils.toTitle("Hello"));
        assertEquals("Hello World", StringUtils.toTitle("Hello World"));
        assertEquals("Hello, World", StringUtils.toTitle("Hello, World"));
    }

    @Test
    public void testToCamelCase_forNullString() {
        assertNull(StringUtils.toCamelCase(null));
    }

    @Test
    public void testToCamelCase_forEmptyString() {
        assertEquals("", StringUtils.toCamelCase(""));
    }

    @Test
    public void testToCamelCase_forValidString() {
        assertEquals("Hello", StringUtils.toCamelCase("Hello"));
        assertEquals("HelloWorld", StringUtils.toCamelCase("Hello World"));
        assertEquals("Hello,World", StringUtils.toCamelCase("Hello, World"));
    }

    @Test
    public void testToSnakeCase_forNullString() {
        assertNull(StringUtils.toSnakeCase(null));
    }

    @Test
    public void testToSnakeCase_forEmptyString() {
        assertEquals("", StringUtils.toSnakeCase(""));
    }

    @Test
    public void testToSnakeCase_forValidString() {
        assertEquals("hello", StringUtils.toSnakeCase("Hello"));
        assertEquals("hello_world", StringUtils.toSnakeCase("Hello World"));
        assertEquals("hello,_world", StringUtils.toSnakeCase("Hello, World"));
    }

    @Test
    public void testToTrim_forNullString() {
        assertNull(StringUtils.toTrim(null));
    }

    @Test
    public void testToTrim_forEmptyString() {
        assertEquals("", StringUtils.toTrim("      "));
    }

    @Test
    public void testToTrim_forValidString() {
        assertEquals("Hello", StringUtils.toTrim("    Hello"));
        assertEquals("Hello World", StringUtils.toTrim("Hello World    "));
        assertEquals("Hello, World", StringUtils.toTrim("     Hello, World"));
        assertEquals("Hello,  World", StringUtils.toTrim("     Hello,  World    "));
    }

    @Test
    public void testToTrimAll_forNullString() {
        assertNull(StringUtils.toTrimAll(null));
    }

    @Test
    public void testToTrimAll_forEmptyString() {
        assertEquals("", StringUtils.toTrimAll("      "));
    }

    @Test
    public void testToTrimAll_forValidString() {
        assertEquals("Hello", StringUtils.toTrimAll("    Hello"));
        assertEquals("HelloWorld", StringUtils.toTrimAll("Hello World    "));
        assertEquals("Hello,World", StringUtils.toTrimAll("     Hello, World"));
        assertEquals("Hello,World", StringUtils.toTrimAll("     Hello,  World    "));
        assertEquals("Hello,World", StringUtils.toTrimAll("         Hello,  World    "));
    }

    @Test
    public void testToMatch_forNullString() {
        assertTrue(StringUtils.toMatch(null, "^[a-zA-Z0-9]*$"));
    }

    @Test
    public void testToMatch_forEmptyString() {
        assertTrue(StringUtils.toMatch("", "^[a-zA-Z0-9]*$"));
        assertFalse(StringUtils.toMatch("     ", "^[a-zA-Z0-9]*$"));
    }

    @Test
    public void testToMatch_forValidString() {
        assertFalse(StringUtils.toMatch("a   aAX 09", "^[a-zA-Z0-9]*$"));
        assertFalse(StringUtils.toMatch("a   aAX 09  @", "^[a-zA-Z0-9]*$"));
        assertTrue(StringUtils.toMatch("aAX09", "^[a-zA-Z0-9]*$"));
    }

    @Test
    public void testToMatchCaseSensitive_forNullString() {
        assertTrue(StringUtils.toMatchCaseSensitive(null, "^[a-zA-Z0-9]*$"));
    }

    @Test
    public void testToMatchCaseSensitive_forEmptyString() {
        assertTrue(StringUtils.toMatchCaseSensitive("", "^[a-z0-9]*$"));
        assertFalse(StringUtils.toMatchCaseSensitive("     ", "^[a-z0-9]*$"));
    }

    @Test
    public void testToMatchCaseSensitive_forValidString() {
        assertFalse(StringUtils.toMatchCaseSensitive("a   aAX 09", "^[a-zA-Z0-9]*$"));
        assertFalse(StringUtils.toMatchCaseSensitive("a   aAX 09  @", "^[a-zA-Z0-9]*$"));
        assertTrue(StringUtils.toMatchCaseSensitive("aAX09", "^[a-zA-Z0-9]*$"));
        assertFalse(StringUtils.toMatchCaseSensitive("a   aAX 09", "^[a-z0-9]*$"));
        assertTrue(StringUtils.toMatchCaseSensitive("ax09", "^[a-z0-9]*$"));
    }

    @Test
    public void testIsEmpty_forEmptyString() {
        assertTrue(StringUtils.isEmpty(""));
    }

    @Test
    public void testIsEmpty_forNullString() {
        assertTrue(StringUtils.isEmpty(null));
    }

    @Test
    public void testIsEmpty_forString() {
        assertFalse(StringUtils.isEmpty("hello"));
    }

    @Test
    public void testIsNotEmpty_forEmptyString() {
        assertFalse(StringUtils.isNotEmpty(""));
    }

    @Test
    public void testIsNotEmpty_forNullString() {
        assertFalse(StringUtils.isNotEmpty(null));
    }

    @Test
    public void testIsNotEmpty_forString() {
        assertTrue(StringUtils.isNotEmpty("hello"));
    }

    @Test
    public void testIsBlank_forEmptyString() {
        assertTrue(StringUtils.isBlank(""));
    }

    @Test
    public void testIsBlank_forNullString() {
        assertTrue(StringUtils.isBlank(null));
    }

    @Test
    public void testIsBlank_forString() {
        assertFalse(StringUtils.isBlank("hello"));
    }

    @Test
    public void testIsNotBlank_forEmptyString() {
        assertFalse(StringUtils.isNotBlank(""));
    }

    @Test
    public void testIsNotBlank_forNullString() {
        assertFalse(StringUtils.isNotBlank(null));
    }

    @Test
    public void testIsNotBlank_forString() {
        assertTrue(StringUtils.isNotBlank("hello"));
    }

    @Test
    public void testToStatementCase_forEmptyString() {
        assertEquals(StringUtils.toStatementCase(""), "");
    }

    @Test
    public void testToStatementCase_forNullString() {
        assertEquals(StringUtils.toStatementCase(null), null);
    }

    @Test
    public void testToStatementCase_forString() {
        assertEquals(StringUtils.toStatementCase("hello"), "hello");
        assertEquals(StringUtils.toStatementCase("Hello World"), "hello world");
        assertEquals(StringUtils.toStatementCase("Hello, World"), "hello, world");
    }

    @Test
    public void testFilterEmoji_forEmptyString() {
        assertEquals(StringUtils.filterEmoji(""), "");
        assertEquals(StringUtils.filterEmoji("\uD83D\uDE00"), "");
    }

    @Test
    public void testFilterEmoji_forNullString() {
        assertEquals(StringUtils.filterEmoji(null), null);
    }

    @Test
    public void testFilterEmoji_forString() {
        assertEquals(StringUtils.filterEmoji("hel\uD83D\uDE00lo"), "hello");
        assertEquals(StringUtils.filterEmoji("\uD83D\uDE00Hello World"), "Hello World");
        assertEquals(StringUtils.filterEmoji("Hello World\uD83D\uDE00"), "Hello World");
        assertEquals(StringUtils.filterEmoji("Hello World\uD83D\uDE00\uD83D\uDE00"), "Hello World");
    }

    @Test
    public void testBase64Encoding_forNullString() {
        String input = null;
        String encoded = StringUtils.toBase64Encode(input);
        String decoded = StringUtils.toBase64Decode(encoded);
        assertEquals(input, decoded);
    }

    @Test
    public void testBase64Encoding_forEmptyString() {
        String input = "";
        String encoded = StringUtils.toBase64Encode(input);
        String decoded = StringUtils.toBase64Decode(encoded);
        assertEquals(input, decoded);
    }

    @Test
    public void testBase64Encoding_forValidValue() {
        String input = "Hello World";
        String encoded = StringUtils.toBase64Encode(input);
        String decoded = StringUtils.toBase64Decode(encoded);
        assertEquals(input, decoded);
    }

}
