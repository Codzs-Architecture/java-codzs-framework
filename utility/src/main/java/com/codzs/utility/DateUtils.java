package com.codzs.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    public static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    public static final String TIME_FORMAT_24_HOUR = "HH:mm:ss";
    public static final String TIME_FORMAT_24_HOUR_NO_SECONDS = "HH:mm";
    public static final String TIME_FORMAT_12_HOUR = "hh:mm:ss a";
    public static final String TIME_FORMAT_12_HOUR_NO_SECONDS = "hh:mm a";
    public static final String DATE_TIME_FORMAT_12_HOUR = "MM/dd/yyyy hh:mm:ss a";
    public static final String DATE_TIME_FORMAT_24_HOUR = "MM/dd/yyyy HH:mm:ss";
    public static final String DATE_TIME_FORMAT_12_HOUR_NO_SECONDS = "MM/dd/yyyy hh:mm a";
    public static final String DATE_TIME_FORMAT_24_HOUR_NO_SECONDS = "MM/dd/yyyy HH:mm";

    public static Date getDate(String date, String format) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
//            System.err.println("Error parsing date: " + date + " with format: " + format);
//            e.printStackTrace();
            return null;
        }
    }

    public static Date getNextDate(Date date) {
        return addDays(date, 1);
    }

    public static Date getPreviousDate(Date date) {
        return addDays(date, -1);
    }

    public static int getDayOfWeek(Date date) {
        if (date == null) {
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static Date getNextDayOfWeek(Date date, int dayOfWeek) {
        int days = dayOfWeek - getDayOfWeek(date);
        if (days <= 0) {
            days += 7;
        }
        return addDays(date, days);
    }

    public static Date getPreviousDayOfWeek(Date date, int dayOfWeek) {
        int days = getDayOfWeek(date) - dayOfWeek;
        if (days <= 0) {
            days += 7;
        }
        return addDays(date, -days);
    }

    public static String getDateAsString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartOfDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 000);
        return calendar.getTime();
    }

    public static int getDaysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static long getTimeInMillis(String dateTimeString, String format) {
        if (StringUtils.isEmpty(dateTimeString)) {
            return 0;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateTimeString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static String addDays(String date, int days, String format) {
        Date d = getDate(date, format);
        d = addDays(d, days);
        return getDateAsString(d, format);
    }

    public static boolean isValidDateTimeString(String dateTimeString, String format) {
        if (StringUtils.isEmpty(dateTimeString)) {
            return false;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(dateTimeString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String toString(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isEmpty(format)) {
            format = DATE_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static boolean isValidMilitaryTime(String time) {
        if (StringUtils.isEmpty(time)) {
            return false;
        }

        long colonCount = time.chars().filter(ch -> ch == ':').count();

        if (colonCount == 1) {
            return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
        } else {
            return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
        }
    }

    public static boolean isBetween(Date date, Date startDate, Date endDate) {
        if (date == null || startDate == null || endDate == null)
            return false;

        return !date.before(startDate) && !date.after(endDate);
    }
}
