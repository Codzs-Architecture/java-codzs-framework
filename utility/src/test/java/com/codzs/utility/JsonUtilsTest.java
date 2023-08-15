package com.codzs.utility;

import com.codzs.utility.StringUtils;
import com.codzs.utility.sample.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codzs.utility.JsonUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class JsonUtilsTest {
    @Test
    public void testFromJson_forNullString() {
        String jsonString = null;
        Map<String, Object> map = fromJson(jsonString);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testFromJson_forEmptyString() {
        String jsonString = "";
        Map<String, Object> map = fromJson(jsonString);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testFromJson_forMap() {
        String jsonString = "{\"a\":1,\"b\":2}";
        Map<String, Object> map = fromJson(jsonString);
        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
    }

    @Test
    public void testFromJson_forNestedMap() {
        String jsonString = "{\"a\":1,\"b\":2,\"c\":{\"d\":3,\"e\":4}}";
        Map<String, Object> map = fromJson(jsonString);
        assertEquals(1, map.get("a"));
        assertEquals(2, map.get("b"));
        assertEquals(2, map.get("b"));
        assertEquals(3, ((Map<String, Object>) map.get("c")).get("d"));
        assertEquals(4, ((Map<String, Object>) map.get("c")).get("e"));
    }

    @Test
    public void testFromJson_intoClass_forNullString() {
        String jsonString = null;
        User user = fromJson(jsonString, User.class);
        assertNull(user);
    }

    @Test
    public void testFromJson_intoClass_forEmptyString() {
        String jsonString = "";
        User user = fromJson(jsonString, User.class);
        assertNull(user);
    }

    @Test
    public void testFromJson_intoClass_forValidJson() {
        String jsonString = "{\"name\":\"Nitin\",\"age\":20,\"address\":{\"city\":\"Melbourne\",\"country\":\"Australia\"}}";
        User user = fromJson(jsonString, User.class);
        assertEquals("Nitin", user.name);
        assertEquals(20, user.age);
        assertEquals("Melbourne", user.address.get("city"));
        assertEquals("Australia", user.address.get("country"));
    }

    @Test
    public void testToJson_forNullMap() {
        Map<String, Object> map = null;
        assertEquals("", toJson(map));
    }

    @Test
    public void testToJson_forEmptyMap() {
        Map<String, Object> map = new HashMap<>();
        assertEquals("", toJson(map));
    }

    @Test
    public void testToJson_forMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        assertEquals("{\"a\":1,\"b\":2}", toJson(map));
    }

    @Test
    public void testToJson_forNestedMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", new HashMap<String, Object>() {{
            put("d", 3);
            put("e", 4);
        }});
        assertEquals("{\"a\":1,\"b\":2,\"c\":{\"d\":3,\"e\":4}}", toJson(map));
    }

    @Test
    public void testToJsonPretty_forNullMap() {
        Map<String, Object> map = null;
        assertEquals("", toJsonPretty(map));
    }

    @Test
    public void testToJsonPretty_forEmptyMap() {
        Map<String, Object> map = new HashMap<>();
        assertEquals("", toJsonPretty(map));
    }

    @Test
    public void testToJsonPretty_forMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        assertEquals("{\n  \"a\" : 1,\n  \"b\" : 2\n}", toJsonPretty(map));
    }

    @Test
    public void testToJsonPretty_forOneLevelNestedMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", new HashMap<String, Object>() {{
            put("d", 3);
            put("e", 4);
        }});
        assertEquals("{\n  \"a\" : 1,\n  \"b\" : 2,\n  \"c\" : {\n    \"d\" : 3,\n    \"e\" : 4\n  }\n}", toJsonPretty(map));
    }

    @Test
    public void testToJsonPretty_forTwoLevelNestedMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", new HashMap<String, Object>() {{
            put("d", 3);
            put("e", 4);
        }});
        map.put("f", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("g", 5);
                put("h", 6);
            }});
            add(new HashMap<String, Object>() {{
                put("i", 7);
                put("j", 8);
            }});
        }});
        assertTrue(StringUtils.isNotEmpty(toJsonPretty(map)));
    }

    @Test
    public void testToJsonPretty_forMultipleNestedMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", new HashMap<String, Object>() {{
            put("d", 3);
            put("e", 4);
        }});
        map.put("f", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("g", 5);
                put("h", 6);
            }});
            add(new HashMap<String, Object>() {{
                put("i", 7);
                put("j", 8);
            }});
        }});
        map.put("k", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("l", 9);
                put("m", 10);
            }});
            add(new HashMap<String, Object>() {{
                put("n", 11);
                put("o", 12);
            }});
        }});
        assertTrue(StringUtils.isNotEmpty(toJsonPretty(map)));
    }

    @Test
    public void testToJsonPretty_forMultipleListOfMaps() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", new HashMap<String, Object>() {{
            put("d", 3);
            put("e", 4);
        }});
        map.put("f", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("g", 5);
                put("h", 6);
            }});
            add(new HashMap<String, Object>() {{
                put("i", 7);
                put("j", 8);
            }});
        }});
        map.put("k", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("l", 9);
                put("m", 10);
            }});
            add(new HashMap<String, Object>() {{
                put("n", 11);
                put("o", 12);
            }});
        }});
        map.put("p", new ArrayList<Map<String, Object>>() {{
            add(new HashMap<String, Object>() {{
                put("q", 13);
                put("r", 14);
            }});
            add(new HashMap<String, Object>() {{
                put("s", 15);
                put("t", 16);
            }});
        }});
        assertTrue(StringUtils.isNotEmpty(toJsonPretty(map)));
    }

    @Test
    public void testToJson_forObject_forNullMap() {
       User user = null;
        assertEquals("", toJson(user));
    }

    @Test
    public void testToJson_forObject_forNestedMap() {
        User user = new User();
        user.name = "Nitin";
        user.age = 30;
        user.address = new HashMap<>();
        user.address.put("city", "Melbourne");
        user.address.put("country", "Australia");
        assertEquals("{\"name\":\"Nitin\",\"age\":30,\"address\":{\"country\":\"Australia\",\"city\":\"Melbourne\"}}", toJson(user));
    }

    @Test
    public void testToJsonPretty_forObject_forNullMap() {
        User user = null;
        assertEquals("", toJsonPretty(user));
    }

    @Test
    public void testToJsonPretty_forObject_forNestedMap() {
        User user = new User();
        user.name = "Nitin";
        user.age = 30;
        user.address = new HashMap<>();
        user.address.put("city", "Melbourne");
        user.address.put("country", "Australia");
        assertTrue(StringUtils.isNotEmpty(toJsonPretty(user)));
    }

    // test case for boolean isValidJson(String json)
    @Test
    public void testIsValidJson_forNull() {
        assertFalse(isValidJson(null));
    }

    @Test
    public void testIsValidJson_forEmptyString() {
        assertFalse(isValidJson(""));
    }

    @Test
    public void testIsValidJson_forInvalidJson() {
        assertFalse(isValidJson("abc"));
    }

    @Test
    public void testIsValidJson_forValidJson() {
        assertTrue(isValidJson("{\"name\":\"Nitin\",\"age\":30,\"address\":{\"country\":\"Australia\",\"city\":\"Melbourne\"}}"));
    }
}