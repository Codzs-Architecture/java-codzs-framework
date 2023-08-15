package com.codzs.utility;

import com.codzs.utility.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtilsTest {
    @Test
    public void testIsEmpty_forEmptyMap() {
        assertTrue(CollectionUtils.isEmpty(new HashMap<>()));
    }

    @Test
    public void testIsEmpty_forEmptySet() {
        assertTrue(CollectionUtils.isEmpty(new HashSet<>()));
    }

    @Test
    public void testIsEmpty_forEmptyList() {
        assertTrue(CollectionUtils.isEmpty(new ArrayList<>()));
    }

    @Test
    public void testIsEmpty_forNullMap() {
        Map<String, Object> map = null;
        assertTrue(CollectionUtils.isEmpty(map));
    }

    @Test
    public void testIsEmpty_forNullSet() {
        Set<String> set = null;
        assertTrue(CollectionUtils.isEmpty(set));
    }

    @Test
    public void testIsEmpty_forNullList() {
        List<String> list = null;
        assertTrue(CollectionUtils.isEmpty(list));
    }

    @Test
    public void testIsNotEmpty_forMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        assertTrue(CollectionUtils.isNotEmpty(map));
    }

    @Test
    public void testIsNotEmpty_forSet() {
        Set<String> set = new HashSet<>();
        set.add("key");
        assertTrue(CollectionUtils.isNotEmpty(set));
    }

    @Test
    public void testIsNotEmpty_forList() {
        List<String> list = new ArrayList<>();
        list.add("key");
        assertTrue(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void testIsNotEmpty_forNullMap() {
        Map<String, Object> map = null;
        assertFalse(CollectionUtils.isNotEmpty(map));
    }

    @Test
    public void testIsNotEmpty_forNullSet() {
        Set<String> set = null;
        assertFalse(CollectionUtils.isNotEmpty(set));
    }

    @Test
    public void testIsNotEmpty_forNullList() {
        List<String> list = null;
        assertFalse(CollectionUtils.isNotEmpty(list));
    }

    @Test
    public void testToString_forEmptyList() {
        List<String> list = new ArrayList<>();
        assertNull(CollectionUtils.toString(list, ","));
    }

    @Test
    public void testToString_forList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals(CollectionUtils.toString(list, ","), "a,b,c");
    }

    @Test
    public void testToString_forNullList() {
        List<String> list = null;
        assertNull(CollectionUtils.toString(list, ","));
    }

    @Test
    public void testToString_forEmptySet() {
        Set<String> set = new HashSet<>();
        assertNull(CollectionUtils.toString(set, ","));
    }

    @Test
    public void testToString_forSet() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        assertEquals(CollectionUtils.toString(set, ","), "a,b,c");
    }

    @Test
    public void testToString_forNullSet() {
        Set<String> set = null;
        assertNull(CollectionUtils.toString(set, ","));
    }
}
