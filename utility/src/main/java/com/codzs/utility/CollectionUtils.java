package com.codzs.utility;

import java.util.Collection;
import java.util.Map;

public class CollectionUtils {
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isNotEmpty(Map<?, ?> m) {
        return !isEmpty(m);
    }

    public static String toString(Collection<String> c, String saperator) {
        if (isEmpty(c)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : c) {
            sb.append(s).append(saperator);
        }
        return sb.substring(0, sb.length() - saperator.length());
    }
}
