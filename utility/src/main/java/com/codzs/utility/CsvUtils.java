package com.codzs.utility;

import java.util.HashMap;
import java.util.Map;

public class CsvUtils {
    public static String toCsv(String[] s) {
        if (s == null) {
            return "";
        }
        return String.join(",", s);
    }

    public static String[] fromCsv(String s) {
        if (s == null) {
            return new String[0];
        }
        return s.split(",");
    }

    public static Map<String, Object> readCsvToMap(String csv) {
        Map<String, Object> map = new HashMap<>();
        String[] lines = csv.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            map.put(parts[0], parts[1]);
        }
        return map;
    }

    public static String writeMapToCsv(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public static int readRecordCount(String csv) {
        String[] lines = csv.split("\n");
        return lines.length;
    }

    public static int readColumnCount(String csv) {
        String[] lines = csv.split("\n");
        return lines[0].split(",").length;
    }
}
