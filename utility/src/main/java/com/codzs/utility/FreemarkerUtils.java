package com.codzs.utility;

import freemarker.template.Configuration;

public class FreemarkerUtils {
    public static String resolveTemplate(String template, java.util.Map<String, Object> data) {
        if (data == null) {
            return template;
        }
        try {
            Configuration cfg = new Configuration();
            freemarker.template.Template temp = new freemarker.template.Template("name", template, cfg);
            java.io.StringWriter out = new java.io.StringWriter();
            temp.process(data, out);
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
