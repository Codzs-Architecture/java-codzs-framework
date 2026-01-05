package com.codzs.utility;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ObjectUtils {
    public static Object deepCopy(Object object) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
            oos.writeObject(object);
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(baos.toByteArray());
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }

    public static byte[] toByteArray(Object object) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object toObject(byte[] bytes) {
        try {
            if (bytes == null) {
                return null;
            }
            java.io.ByteArrayInputStream bais = new java.io.ByteArrayInputStream(bytes);
            java.io.ObjectInputStream ois = new java.io.ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isEmpty(Object object) {
        try {
            if (object == null || object == "") {
                return true;
            }

            if (object instanceof Collection) {
                return ((Collection) object).isEmpty();
            } else if (object instanceof Map) {
                return ((Map) object).isEmpty();
            } else if (object instanceof Number) {
                return !(((Number) object).doubleValue() > 0.0);
            } else if (object instanceof Object[]) {
                return !(((Object[]) object).length > 0);
            } else if (object instanceof String) {
                return ((String) object).isEmpty();
            } else {
                return true;
            }
        } catch (final Exception e) {
            return false;
        }
    }

    public static void copyProperties(Object object, Map<String, Object> map, Set<String> skipProperties) {
        try {
            if (object == null || map == null) {
                return;
            }
            BeanInfo beanInfo = java.beans.Introspector.getBeanInfo(object.getClass());
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            for (var property : properties) {
                if (map.containsKey(property.getName()) && !skipProperties.contains(property.getName())) {
                    property.getWriteMethod().invoke(object, map.get(property.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyProperties(Object object, Map<String, Object> map) {
        copyProperties(object, map, Set.of());
    }

    public static boolean equals(Object object1, Object object2) {
        if (object1 == null && object2 == null) {
            return true;
        } else if (object1 == null || object2 == null) {
            return false;
        } else {
            return object1.equals(object2);
        }
    }
}
