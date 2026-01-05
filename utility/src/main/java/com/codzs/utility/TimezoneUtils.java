package com.codzs.utility;

public class TimezoneUtils {
    public static String getDefaultTimezone() {
        return java.util.TimeZone.getDefault().getID();
    }
}
