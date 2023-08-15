package com.codzs.utility;

import com.codzs.utility.ObjectUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectUtilsTest {
    @Test
    public void testDeepCopy_forNullObject() {
        Object object = null;
        Object result = ObjectUtils.deepCopy(object);
        assertEquals(object, result);
    }

    @Test
    public void testDeepCopy_forEmptyObject() {
        Object object = "";
        Object result = ObjectUtils.deepCopy(object);
        assertEquals(object, result);
    }

    @Test
    public void testDeepCopy_forValidObject() {
        List<String> list = new ArrayList<>();
        list.add("a");

        Object result = ObjectUtils.deepCopy(list);
        assertEquals(list, result);
    }

    @Test
    public void testToByteArray_forNullObject() {
        Object object = null;
        byte[] result = ObjectUtils.toByteArray(object);
        Object resultObject = ObjectUtils.toObject(result);
        assertEquals(object, resultObject);
    }

    @Test
    public void testToByteArray_forEmptyObject() {
        Object object = "";
        byte[] result = ObjectUtils.toByteArray(object);
        Object resultObject = ObjectUtils.toObject(result);
        assertEquals(object, resultObject);
    }

    @Test
    public void testToByteArray_forValidObject() {
        List<String> list = new ArrayList<>();
        list.add("a");

        byte[] result = ObjectUtils.toByteArray(list);
        Object resultObject = ObjectUtils.toObject(result);
        assertEquals(list, resultObject);
    }

    @Test
    public void testToObject_forNullObject() {
        byte[] result = null;
        Object resultObject = ObjectUtils.toObject(result);
        assertNull(resultObject);
    }

    @Test
    public void testIsNull_forNullObject() {
        Object object = null;
        boolean result = ObjectUtils.isEmpty(object);
        assertTrue(result);
    }

    @Test
    public void testIsNull_forEmptyObject() {
        Object object = "";
        boolean result = ObjectUtils.isEmpty(object);
        assertTrue(result);
    }

    @Test
    public void testIsNull_forValidList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        boolean result = ObjectUtils.isEmpty(list);
        assertFalse(result);
    }

    @Test
    public void testIsNull_forValidEmptyList() {
        List<String> list = new ArrayList<>();
        boolean result = ObjectUtils.isEmpty(list);
        assertTrue(result);
    }

    @Test
    public void testIsNull_forValidMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        boolean result = ObjectUtils.isEmpty(map);
        assertFalse(result);
    }

    @Test
    public void testIsNull_forValidEmptyMap() {
        Map<String, Integer> map = new HashMap<>();
        boolean result = ObjectUtils.isEmpty(map);
        assertTrue(result);
    }

    @Test
    public void testIsNull_forValidSet() {
        Set<String> set = new HashSet<>();
        set.add("a");
        boolean result = ObjectUtils.isEmpty(set);
        assertFalse(result);
    }

    @Test
    public void testIsNull_forValidEmptySet() {
        Set<String> set = new HashSet<>();
        boolean result = ObjectUtils.isEmpty(set);
        assertTrue(result);
    }

    @Test
    public void testCopyProperties_forNullObject() {
        Object object = null;
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        Set<String> skipProperties = new HashSet<>();
        skipProperties.add("a");
        ObjectUtils.copyProperties(object, map, skipProperties);
        assertNull(object);
    }

    @Test
    public void testCopyProperties_forNullMap() {
        Object object = new Object();
        Map<String, Object> map = null;
        Set<String> skipProperties = new HashSet<>();
        skipProperties.add("a");
        ObjectUtils.copyProperties(object, map, skipProperties);
        assertNotNull(object);
    }

    @Test
    public void testCopyProperties_forNullSkipProperties() {
        Object object = new Object();
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        Set<String> skipProperties = null;
        ObjectUtils.copyProperties(object, map, skipProperties);
        assertNotNull(object);
    }

    @Test
    public void testCopyProperties_forValidObject() {
        Object object = new Object();
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        Set<String> skipProperties = new HashSet<>();
        skipProperties.add("a");
        ObjectUtils.copyProperties(object, map, skipProperties);
        assertNotNull(object);
    }

    @Test
    public void testCopyPropertiesWithoutSkip_forNullObject() {
        Object object = null;
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        ObjectUtils.copyProperties(object, map);
        assertNull(object);
    }

    @Test
    public void testCopyPropertiesWithoutSkip_forNullMap() {
        Object object = new Object();
        Map<String, Object> map = null;
        ObjectUtils.copyProperties(object, map);
        assertNotNull(object);
    }

    @Test
    public void testCopyPropertiesWithoutSkip_forValidObject() {
        Object object = new Object();
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        ObjectUtils.copyProperties(object, map);
        assertNotNull(object);
    }

}
