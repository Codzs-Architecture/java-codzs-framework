package com.codzs.utility;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyUtils {
    public static Properties loadSystemProperties(String propertyFile) {
        final Properties defaultedProps = new Properties(System.getProperties());
        final URL url = Thread.currentThread().getContextClassLoader().getResource(propertyFile);
        if (url == null) {
            return defaultedProps;
        }
        try {
            final Properties fileProps = new Properties();
            try (InputStream is = url.openStream()) {
                fileProps.load(is);
            }
            final Enumeration<?> names = fileProps.propertyNames();
            while (names.hasMoreElements()) {
                final String name = (String) names.nextElement();
                if (System.getProperty(name) == null) {
                    defaultedProps.setProperty(name, fileProps.getProperty(name));
                }
            }
        } catch (final Exception ex) {
            System.err.println("Could not load '" + propertyFile + "' from classpath: " + ex);
        }
        return defaultedProps;

    }
}
