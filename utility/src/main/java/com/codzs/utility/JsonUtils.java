package com.codzs.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonUtils {
    public static Map<String, Object> fromJson(ObjectMapper objectMapper, String jsonAsString) {
        if (StringUtils.isNotBlank(jsonAsString)) {
            try {
                return objectMapper.readValue(jsonAsString, new TypeReference<Map<String, Object>>() {
                });
            } catch (final IOException ex) {
                throw new IllegalArgumentException(ex.getMessage(), ex);
            }
        } else {
            return new HashMap<>();
        }
    }

    public static Map<String, Object> fromJson(String jsonAsString) {
        return fromJson(new ObjectMapper(), jsonAsString);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }

        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(ObjectMapper objectMapper, Map<String, Object> map) {
        String jsonAsString = "";

        if (CollectionUtils.isNotEmpty(map)) {
            try {
                jsonAsString = objectMapper.writeValueAsString(map);
            } catch (final IOException ex) {
                throw new IllegalArgumentException(ex.getMessage(), ex);
            }
        }
        return jsonAsString;
    }

    public static String toJson(Map<String, Object> map) {
        return toJson(new ObjectMapper(), map);
    }

    public static String toJsonPretty(Map<String, Object> map) {
        String jsonAsString = "";

        if (CollectionUtils.isNotEmpty(map)) {
            try {
                jsonAsString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(map);
            } catch (final IOException e) {
                //System.err.println(e.getMessage(), e);
            }
        }
        return jsonAsString;
    }

    public static String toJson(Object object) {
        String jsonAsString = "";

        if (object != null) {
            try {
                jsonAsString = new ObjectMapper().writeValueAsString(object);
            } catch (Exception e) {
                e.printStackTrace();
                jsonAsString = null;
            }
        }

        return jsonAsString;
    }

    public static String toJsonPretty(Object object) {
        String jsonAsString = "";

        if (object != null) {
            try {
                jsonAsString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } catch (Exception e) {
                e.printStackTrace();
                jsonAsString = null;
            }
        }

        return jsonAsString;
    }

    public static boolean isValidJson(String json) {
        boolean valid = false;

        if (StringUtils.isNotBlank(json)) {
            try {
                new ObjectMapper().readTree(json);
                valid = true;
            } catch (Exception e) {
                //System.err.println(e.getMessage());
                valid = false;
            }
        }

        return valid;
    }
}
