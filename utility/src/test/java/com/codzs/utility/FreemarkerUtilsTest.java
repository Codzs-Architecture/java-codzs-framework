package com.codzs.utility;

import com.codzs.utility.FreemarkerUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FreemarkerUtilsTest {
    @Test
    public void testResolveTemplate_forNullTemplate() {
        String template = null;
        java.util.Map<String, Object> data = null;
        String result = FreemarkerUtils.resolveTemplate(template, data);
        assertNull(result);
    }

    @Test
    public void testResolveTemplate_forEmptyTemplate() {
        String template = "";
        java.util.Map<String, Object> data = null;
        String result = FreemarkerUtils.resolveTemplate(template, data);
        assertEquals(template, result);
    }

    @Test
    public void testResolveTemplate_forValidTemplate() {
        String template = "Hi ${name}";
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("name", "Nitin");
        String result = FreemarkerUtils.resolveTemplate(template, data);
        assertEquals("Hi Nitin", result);
    }

    @Test
    public void testResolveTemplate_forValidTemplate2() {
        String template = "Hi ${name}, your age is ${age}";
        java.util.Map<String, Object> data = new java.util.HashMap<>();
        data.put("name", "Nitin");
        data.put("age", 30);
        String result = FreemarkerUtils.resolveTemplate(template, data);
        assertEquals(result, "Hi Nitin, your age is 30");
    }
}
