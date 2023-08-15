package com.codzs.utility;

public class ReflectionUtils {
    // to write a method to get class instance by class name
    public static Class<?> getClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // to get a class instance by class name and constructor parameters
    public static <T> T getInstance(String className, Class<?>[] parameterTypes, Object[] parameterValues) {
        try {
            Class<?> clazz = Class.forName(className);
            return (T) clazz.getConstructor(parameterTypes).newInstance(parameterValues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
