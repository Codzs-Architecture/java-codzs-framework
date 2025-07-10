package com.codzs.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DateUtilsTest {
    
    // Helper method to create Date objects without using deprecated constructor
    private Date createDate(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + dateString, e);
        }
    }
    
    private Date createDateTime(String dateTimeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            return sdf.parse(dateTimeString);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse datetime: " + dateTimeString, e);
        }
    }
    
    @Test
    public void testGetDate_forNullDate() {
        assertNull(DateUtils.getDate(null, null));
    }

    @Test
    public void testGetDate_forEmptyDate() {
        assertNull(DateUtils.getDate("", null));
    }

    @Test
    public void testGetDate_forValidDate() {
        // Test that valid dates are parsed successfully
        assertNotNull(DateUtils.getDate("01/01/2020", null));
        assertNotNull(DateUtils.getDate("31/01/2020", null));
        assertNotNull(DateUtils.getDate("01/31/2020", null));
        assertNotNull(DateUtils.getDate("01/31/2020", DateUtils.DATE_FORMAT));
        assertNotNull(DateUtils.getDate("01/31/2020 12:00:00", DateUtils.DATE_TIME_FORMAT));
        assertNotNull(DateUtils.getDate("01/31/2020 12:00:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR));
        assertNotNull(DateUtils.getDate("01/31/2020 12:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR_NO_SECONDS));
        assertNotNull(DateUtils.getDate("01/31/2020 13:01:01", DateUtils.DATE_TIME_FORMAT_24_HOUR));
        assertNotNull(DateUtils.getDate("01/31/2020 13:01", DateUtils.DATE_TIME_FORMAT_24_HOUR_NO_SECONDS));
    }

    @Test
    public void testGetDate_forInValidDate() {
        assertNull(DateUtils.getDate("01/31", null));
        assertNull(DateUtils.getDate("01/31/1", DateUtils.DATE_TIME_FORMAT));
        assertNull(DateUtils.getDate("01-31-2020", DateUtils.DATE_FORMAT));
        assertNull(DateUtils.getDate("2022-01-01", DateUtils.DATE_FORMAT));
        assertNull(DateUtils.getDate("01/31/2020 12:00", DateUtils.DATE_TIME_FORMAT));

        assertNull(DateUtils.getDate("01/31/2020 12:01", DateUtils.DATE_TIME_FORMAT_12_HOUR_NO_SECONDS));
        assertNull(DateUtils.getDate("01-31-2020 13:01", DateUtils.DATE_TIME_FORMAT_24_HOUR_NO_SECONDS));
    }

    @Test
    public void testGetNextDate_forNullDate() {
        assertNull(DateUtils.getNextDate(null));
    }

    @Test
    public void testGetNextDate_forValidDate() {
        assertEquals(createDate("01/02/2020"), DateUtils.getNextDate(createDate("01/01/2020")));
        assertEquals(createDateTime("01/02/2020 12:00:00"), DateUtils.getNextDate(createDateTime("01/01/2020 12:00:00")));
        assertEquals(createDateTime("01/02/2020 12:00:01"), DateUtils.getNextDate(createDateTime("01/01/2020 12:00:01")));
        assertEquals(createDateTime("01/02/2020 12:00:01"), DateUtils.getNextDate(createDateTime("01/01/2020 12:00:01")));
        assertEquals(createDateTime("01/02/2020 12:00:01"), DateUtils.getNextDate(createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetPreviousDate_forNullDate() {
        assertNull(DateUtils.getPreviousDate(null));
    }

    @Test
    public void testGetPreviousDate_forValidDate() {
        assertEquals(createDate("01/01/2020"), DateUtils.getPreviousDate(createDate("01/02/2020")));
        assertEquals(createDateTime("01/01/2020 12:00:00"), DateUtils.getPreviousDate(createDateTime("01/02/2020 12:00:00")));
        assertEquals(createDateTime("01/01/2020 12:00:01"), DateUtils.getPreviousDate(createDateTime("01/02/2020 12:00:01")));
        assertEquals(createDateTime("01/01/2020 12:00:01"), DateUtils.getPreviousDate(createDateTime("01/02/2020 12:00:01")));
        assertEquals(createDateTime("01/01/2020 12:00:01"), DateUtils.getPreviousDate(createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forNullDate() {
        assertEquals(0, DateUtils.getDaysBetween(null, null));
    }

    @Test
    public void testGetDateDifference_forValidDate() {
        assertEquals(1, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/02/2020")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/02/2020 12:00:00")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forInValidDate() {
        assertEquals(0, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/01/2020")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/01/2020 12:00:00")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forValidDateAndTime() {
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/02/2020 12:00:00")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forInValidDateAndTime() {
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/01/2020 12:00:00")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forValidDateAndTimeAndSeconds() {
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/02/2020 12:00:00")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forInValidDateAndTimeAndSeconds() {
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/01/2020 12:00:00")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forValidDateAndTimeAndSecondsAndMilliSeconds() {
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/02/2020 12:00:00")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forInValidDateAndTimeAndSecondsAndMilliSeconds() {
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/01/2020 12:00:00")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forValidDateAndTimeAndSecondsAndMilliSecondsAndTimeZone() {
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/02/2020 12:00:00")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
        assertEquals(1, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/02/2020 12:00:01")));
    }

    @Test
    public void testGetDateDifference_forInValidDateAndTimeAndSecondsAndMilliSecondsAndTimeZone() {
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:00"), createDateTime("01/01/2020 12:00:00")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
        assertEquals(0, DateUtils.getDaysBetween(createDateTime("01/01/2020 12:00:01"), createDateTime("01/01/2020 12:00:01")));
    }

    @Test
    public void testGetDayOfWeek_forNullDate() {
        assertEquals(-1, DateUtils.getDayOfWeek(null));
    }

    @Test
    public void testGetDayOfWeek_forValidDate() {
        assertEquals(4, DateUtils.getDayOfWeek(createDate("01/01/2020")));
        assertEquals(5, DateUtils.getDayOfWeek(createDate("01/02/2020")));
        assertEquals(6, DateUtils.getDayOfWeek(createDate("01/03/2020")));
        assertEquals(7, DateUtils.getDayOfWeek(createDate("01/04/2020")));
        assertEquals(1, DateUtils.getDayOfWeek(createDate("01/05/2020")));
        assertEquals(2, DateUtils.getDayOfWeek(createDate("01/06/2020")));
        assertEquals(3, DateUtils.getDayOfWeek(createDate("01/07/2020")));
    }

    @Test
    public void testGetDayOfWeek_forValidDateAndTime() {
        assertEquals(4, DateUtils.getDayOfWeek(createDateTime("01/01/2020 12:00:00")));
        assertEquals(5, DateUtils.getDayOfWeek(createDateTime("01/02/2020 12:00:00")));
        assertEquals(6, DateUtils.getDayOfWeek(createDateTime("01/03/2020 12:00:00")));
        assertEquals(7, DateUtils.getDayOfWeek(createDateTime("01/04/2020 12:00:00")));
        assertEquals(1, DateUtils.getDayOfWeek(createDateTime("01/05/2020 12:00:00")));
        assertEquals(2, DateUtils.getDayOfWeek(createDateTime("01/06/2020 12:00:00")));
        assertEquals(3, DateUtils.getDayOfWeek(createDateTime("01/07/2020 12:00:00")));
    }

    @Test
    public void testGetDayOfWeek_forValidDateAndTimeAndSeconds() {
        assertEquals(4, DateUtils.getDayOfWeek(createDateTime("01/01/2020 12:00:00")));
        assertEquals(5, DateUtils.getDayOfWeek(createDateTime("01/02/2020 12:00:00")));
        assertEquals(6, DateUtils.getDayOfWeek(createDateTime("01/03/2020 12:00:00")));
        assertEquals(7, DateUtils.getDayOfWeek(createDateTime("01/04/2020 12:00:00")));
        assertEquals(1, DateUtils.getDayOfWeek(createDateTime("01/05/2020 12:00:00")));
        assertEquals(2, DateUtils.getDayOfWeek(createDateTime("01/06/2020 12:00:00")));
        assertEquals(3, DateUtils.getDayOfWeek(createDateTime("01/07/2020 12:00:00")));
    }

    @Test
    public void testGetDayOfWeek_forValidDateAndTimeAndSecondsAndAMPM() {
        assertEquals(4, DateUtils.getDayOfWeek(createDateTime("01/01/2020 12:00:00")));
        assertEquals(5, DateUtils.getDayOfWeek(createDateTime("01/02/2020 12:00:00")));
        assertEquals(6, DateUtils.getDayOfWeek(createDateTime("01/03/2020 12:00:00")));
        assertEquals(7, DateUtils.getDayOfWeek(createDateTime("01/04/2020 12:00:00")));
        assertEquals(1, DateUtils.getDayOfWeek(createDateTime("01/05/2020 12:00:00")));
        assertEquals(2, DateUtils.getDayOfWeek(createDateTime("01/06/2020 12:00:00")));
        assertEquals(3, DateUtils.getDayOfWeek(createDateTime("01/07/2020 12:00:00")));
    }

    @Test
    public void testGetDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZone() {
        assertEquals(4, DateUtils.getDayOfWeek(createDateTime("01/01/2020 12:00:00")));
        assertEquals(5, DateUtils.getDayOfWeek(createDateTime("01/02/2020 12:00:00")));
        assertEquals(6, DateUtils.getDayOfWeek(createDateTime("01/03/2020 12:00:00")));
        assertEquals(7, DateUtils.getDayOfWeek(createDateTime("01/04/2020 12:00:00")));
        assertEquals(1, DateUtils.getDayOfWeek(createDateTime("01/05/2020 12:00:00")));
        assertEquals(2, DateUtils.getDayOfWeek(createDateTime("01/06/2020 12:00:00")));
        assertEquals(3, DateUtils.getDayOfWeek(createDateTime("01/07/2020 12:00:00")));
    }

    @Test
    public void testGetNextDayOfWeek_forNullDate() {
        assertEquals(null, DateUtils.getNextDayOfWeek(null, 1));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDate() {
        assertEquals(createDate("01/05/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 1));
        assertEquals(createDate("01/06/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 2));
        assertEquals(createDate("01/07/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 3));
        assertEquals(createDate("01/08/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 4));
        assertEquals(createDate("01/02/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 5));
        assertEquals(createDate("01/03/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 6));
        assertEquals(createDate("01/04/2020"), DateUtils.getNextDayOfWeek(createDate("01/01/2020"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTime() {
        assertEquals(createDateTime("01/05/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSeconds() {
        assertEquals(createDateTime("01/05/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSecondsAndAMPM() {
        assertEquals(createDateTime("01/05/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZone() {
        assertEquals(createDateTime("01/05/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocale() {
        assertEquals(createDateTime("01/05/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendar() {
        assertEquals(createDateTime("01/05/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetNextDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZone() {
        assertEquals(createDateTime("01/05/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("01/06/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("01/07/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/08/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("01/02/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("01/03/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("01/04/2020 12:00:00 am"), DateUtils.getNextDayOfWeek(createDateTime("01/01/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetPreviousDayOfWeek_forNullValue() {
        assertEquals(null, DateUtils.getPreviousDayOfWeek(null, 1));
    }

    @Test
    public void testGetPreviousDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZone() {
        assertEquals(createDateTime("12/29/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("12/30/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("12/31/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/01/2020 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("12/26/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("12/27/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("12/28/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetPreviousDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZoneAndLocale() {
        assertEquals(createDateTime("12/29/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("12/30/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("12/31/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/01/2020 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("12/26/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("12/27/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("12/28/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetPreviousDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZoneAndLocaleAndCalendar() {
        assertEquals(createDateTime("12/29/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("12/30/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("12/31/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/01/2020 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("12/26/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("12/27/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("12/28/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetPreviousDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZoneAndLocaleAndCalendarAndTimeZone() {
        assertEquals(createDateTime("12/29/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("12/30/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("12/31/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/01/2020 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("12/26/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("12/27/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("12/28/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 7));
    }

    @Test
    public void testGetPreviousDayOfWeek_forValidDateAndTimeAndSecondsAndAMPMAndTimeZoneAndLocaleAndCalendarAndTimeZoneAndLocaleAndCalendarAndTimeZoneAndLocale() {
        assertEquals(createDateTime("12/29/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 1));
        assertEquals(createDateTime("12/30/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 2));
        assertEquals(createDateTime("12/31/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 3));
        assertEquals(createDateTime("01/01/2020 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 4));
        assertEquals(createDateTime("12/26/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 5));
        assertEquals(createDateTime("12/27/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 6));
        assertEquals(createDateTime("12/28/2019 12:00:00 am"), DateUtils.getPreviousDayOfWeek(createDateTime("01/02/2020 12:00:00 am"), 7));
    }
    @Test
    public void testGetDateAsString_forNullDate() {
        assertNull(DateUtils.getDateAsString(null, null));
    }

    @Test
    public void testGetDateAsString_forValidDate() {
        Date testDate1 = DateUtils.getDate("01/01/2020", null);
        Date testDate2 = DateUtils.getDate("01/31/2020", null);
        Date testDate3 = DateUtils.getDate("01/31/2020 12:00:00", DateUtils.DATE_TIME_FORMAT);
        Date testDate4 = DateUtils.getDate("01/31/2020 12:00:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate5 = DateUtils.getDate("01/31/2020 12:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR_NO_SECONDS);
        Date testDate6 = DateUtils.getDate("01/31/2020 13:01:01", DateUtils.DATE_TIME_FORMAT_24_HOUR);
        Date testDate7 = DateUtils.getDate("01/31/2020 13:01", DateUtils.DATE_TIME_FORMAT_24_HOUR_NO_SECONDS);
        
        assertEquals("01/01/2020", DateUtils.getDateAsString(testDate1, null));
        assertEquals("01/31/2020", DateUtils.getDateAsString(testDate2, null));
        assertEquals("01/31/2020", DateUtils.getDateAsString(testDate2, DateUtils.DATE_FORMAT));
        assertEquals("01/31/2020 12:00:00", DateUtils.getDateAsString(testDate3, DateUtils.DATE_TIME_FORMAT));
        assertEquals("01/31/2020 12:00:01 am", DateUtils.getDateAsString(testDate4, DateUtils.DATE_TIME_FORMAT_12_HOUR));
        assertEquals("01/31/2020 12:01 am", DateUtils.getDateAsString(testDate5, DateUtils.DATE_TIME_FORMAT_12_HOUR_NO_SECONDS));
        assertEquals("01/31/2020 13:01:01", DateUtils.getDateAsString(testDate6, DateUtils.DATE_TIME_FORMAT_24_HOUR));
        assertEquals("01/31/2020 13:01", DateUtils.getDateAsString(testDate7, DateUtils.DATE_TIME_FORMAT_24_HOUR_NO_SECONDS));
    }

    @Test
    public void testGetEndOfDay_forNullDate() {
        assertNull(DateUtils.getEndOfDay(null));
    }

    @Test
    public void testGetEndOfDay_forValidDate() {
        Date testDate1 = DateUtils.getDate("01/01/2020 1:00:00 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate2 = DateUtils.getDate("01/31/2020 1:00:00 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate3 = DateUtils.getDate("01/31/2020 11:59:59 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate4 = DateUtils.getDate("01/31/2020 2:00:00 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate5 = DateUtils.getDate("01/31/2020 1:59:59 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        
        assertEquals(DateUtils.getEndOfDay(testDate1).toString(), DateUtils.getEndOfDay(testDate1).toString());
        assertEquals(DateUtils.getEndOfDay(testDate2).toString(), DateUtils.getEndOfDay(testDate2).toString());
        assertEquals(DateUtils.getEndOfDay(testDate3).toString(), DateUtils.getEndOfDay(testDate3).toString());
        assertEquals(DateUtils.getEndOfDay(testDate4).toString(), DateUtils.getEndOfDay(testDate4).toString());
        assertEquals(DateUtils.getEndOfDay(testDate5).toString(), DateUtils.getEndOfDay(testDate5).toString());
    }

    @Test
    public void testGetStartOfDay_forNullDate() {
        assertNull(DateUtils.getStartOfDay(null));
    }

    @Test
    public void testGetStartOfDay_forValidDate() {
        Date testDate1 = DateUtils.getDate("01/01/2020 12:00:00 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate2 = DateUtils.getDate("01/31/2020 12:00:00 am", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        Date testDate3 = DateUtils.getDate("01/31/2020 11:59:59 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR);
        
        assertEquals(DateUtils.getStartOfDay(testDate1).toString(), DateUtils.getStartOfDay(testDate1).toString());
        assertEquals(DateUtils.getStartOfDay(testDate2).toString(), DateUtils.getStartOfDay(testDate2).toString());
        assertEquals(DateUtils.getStartOfDay(testDate3).toString(), DateUtils.getStartOfDay(testDate3).toString());
    }

    @Test
    public void testGetDaysBetween_forNullDates() {
        assertEquals(0, DateUtils.getDaysBetween(null, null));
    }

    @Test
    public void testGetDaysBetween_forValidDates() {
        assertEquals(0, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/01/2020")));
        assertEquals(1, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/02/2020")));
        assertEquals(2, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/03/2020")));
        assertEquals(3, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/04/2020")));
        assertEquals(4, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/05/2020")));
        assertEquals(5, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/06/2020")));
        assertEquals(6, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/07/2020")));
        assertEquals(7, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/08/2020")));
        assertEquals(8, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/09/2020")));
        assertEquals(9, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/10/2020")));
        assertEquals(10, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/11/2020")));
        assertEquals(11, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/12/2020")));
        assertEquals(12, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/13/2020")));
        assertEquals(13, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/14/2020")));
        assertEquals(14, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/15/2020")));
        assertEquals(15, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/16/2020")));
        assertEquals(16, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/17/2020")));
        assertEquals(17, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/18/2020")));
        assertEquals(18, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/19/2020")));
        assertEquals(19, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/20/2020")));
        assertEquals(20, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/21/2020")));
        assertEquals(21, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/22/2020")));
        assertEquals(22, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/23/2020")));
        assertEquals(23, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/24/2020")));
        assertEquals(24, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/25/2020")));
        assertEquals(25, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/26/2020")));
        assertEquals(26, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/27/2020")));
        assertEquals(27, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/28/2020")));
        assertEquals(28, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/29/2020")));
        assertEquals(29, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/30/2020")));
        assertEquals(30, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("01/31/2020")));
        assertEquals(31, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("02/01/2020")));
        assertEquals(32, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("02/02/2020")));
        assertEquals(33, DateUtils.getDaysBetween(createDate("01/01/2020"), createDate("02/03/2020")));
    }

    @Test
    public void testGetTimeInMillis_forNullDate() {
        assertEquals(0, DateUtils.getTimeInMillis(null, null));
    }

    @Test
    public void testGetTimeInMillis_forValidDate() {
        assertEquals(1577797200000L, DateUtils.getTimeInMillis("01/01/2020", null));
        assertEquals(1577883600000L, DateUtils.getTimeInMillis("01/02/2020", null));
        assertEquals(1577970000000L, DateUtils.getTimeInMillis("01/03/2020", null));
        assertEquals(1578056400000L, DateUtils.getTimeInMillis("01/04/2020", null));
        assertEquals(1578142800000L, DateUtils.getTimeInMillis("01/05/2020", null));
        assertEquals(1578229200000L, DateUtils.getTimeInMillis("01/06/2020", null));
        assertEquals(1578315600000L, DateUtils.getTimeInMillis("01/07/2020", null));
        assertEquals(1578402000000L, DateUtils.getTimeInMillis("01/08/2020", null));
        assertEquals(1578488400000L, DateUtils.getTimeInMillis("01/09/2020", null));
        assertEquals(1578574800000L, DateUtils.getTimeInMillis("01/10/2020", null));
        assertEquals(1578661200000L, DateUtils.getTimeInMillis("01/11/2020", null));
        assertEquals(1578747600000L, DateUtils.getTimeInMillis("01/12/2020", null));
        assertEquals(1578834000000L, DateUtils.getTimeInMillis("01/13/2020", null));
        assertEquals(1578920400000L, DateUtils.getTimeInMillis("01/14/2020", null));
        assertEquals(1579006800000L, DateUtils.getTimeInMillis("01/15/2020", null));
        assertEquals(1579093200000L, DateUtils.getTimeInMillis("01/16/2020", null));
        assertEquals(1579179600000L, DateUtils.getTimeInMillis("01/17/2020", null));
        assertEquals(1579266000000L, DateUtils.getTimeInMillis("01/18/2020", null));
        assertEquals(1579352400000L, DateUtils.getTimeInMillis("01/19/2020", null));
        assertEquals(1579438800000L, DateUtils.getTimeInMillis("01/20/2020", null));
        assertEquals(1579525200000L, DateUtils.getTimeInMillis("01/21/2020", null));
        assertEquals(1579611600000L, DateUtils.getTimeInMillis("01/22/2020", null));
        assertEquals(1579698000000L, DateUtils.getTimeInMillis("01/23/2020", null));
        assertEquals(1579784400000L, DateUtils.getTimeInMillis("01/24/2020", null));
        assertEquals(1579870800000L, DateUtils.getTimeInMillis("01/25/2020", null));
        assertEquals(1579957200000L, DateUtils.getTimeInMillis("01/26/2020", null));
        assertEquals(1580043600000L, DateUtils.getTimeInMillis("01/27/2020", null));
        assertEquals(1580130000000L, DateUtils.getTimeInMillis("01/28/2020", null));
        assertEquals(1580216400000L, DateUtils.getTimeInMillis("01/29/2020", null));
        assertEquals(1580302800000L, DateUtils.getTimeInMillis("01/30/2020", null));
        assertEquals(1580389200000L, DateUtils.getTimeInMillis("01/31/2020", null));
        assertEquals(1580475600000L, DateUtils.getTimeInMillis("02/01/2020", null));
        assertEquals(1580562000000L, DateUtils.getTimeInMillis("02/02/2020", null));
        assertEquals(1580720400000L, DateUtils.getTimeInMillis("02/03/2020 20:00:00", DateUtils.DATE_TIME_FORMAT));
    }

    @Test
    public void testAddDays_forNullDate() {
        assertNull(DateUtils.addDays(null, 0, null));
    }

    @Test
    public void testAddDays_forValidDate() {
        assertEquals("01/02/2020", DateUtils.addDays("01/01/2020", 1, null));
        assertEquals("01/03/2020", DateUtils.addDays("01/01/2020", 2, null));
        assertEquals("01/04/2020", DateUtils.addDays("01/01/2020", 3, null));
        assertEquals("01/05/2020", DateUtils.addDays("01/01/2020", 4, null));
        assertEquals("01/06/2020", DateUtils.addDays("01/01/2020", 5, null));
        assertEquals("01/07/2020", DateUtils.addDays("01/01/2020", 6, null));
        assertEquals("01/08/2020", DateUtils.addDays("01/01/2020", 7, null));
        assertEquals("01/09/2020", DateUtils.addDays("01/01/2020", 8, null));
        assertEquals("01/10/2020", DateUtils.addDays("01/01/2020", 9, null));
        assertEquals("01/11/2020", DateUtils.addDays("01/01/2020", 10, null));
        assertEquals("01/12/2020", DateUtils.addDays("01/01/2020", 11, null));
        assertEquals("01/13/2020", DateUtils.addDays("01/01/2020", 12, null));
        assertEquals("01/14/2020", DateUtils.addDays("01/01/2020", 13, null));
        assertEquals("01/15/2020", DateUtils.addDays("01/01/2020", 14, null));
        assertEquals("01/16/2020", DateUtils.addDays("01/01/2020", 15, null));
        assertEquals("01/17/2020", DateUtils.addDays("01/01/2020", 16, null));
        assertEquals("01/18/2020", DateUtils.addDays("01/01/2020", 17, null));
        assertEquals("01/19/2020", DateUtils.addDays("01/01/2020", 18, null));
    }

    @Test
    public void testAddDays_forNullDate_withFormat() {
        assertNull(DateUtils.addDays(null, 0, DateUtils.DATE_FORMAT));
    }

    @Test
    public void testAddDays_forValidDate_withFormat() {
        assertEquals("01/02/2020", DateUtils.addDays("01/01/2020", 1, DateUtils.DATE_FORMAT));
        assertEquals("01/03/2020", DateUtils.addDays("01/01/2020", 2, DateUtils.DATE_FORMAT));
        assertEquals("01/04/2020", DateUtils.addDays("01/01/2020", 3, DateUtils.DATE_FORMAT));
        assertEquals("01/05/2020", DateUtils.addDays("01/01/2020", 4, DateUtils.DATE_FORMAT));
        assertEquals("01/06/2020", DateUtils.addDays("01/01/2020", 5, DateUtils.DATE_FORMAT));
        assertEquals("01/07/2020", DateUtils.addDays("01/01/2020", 6, DateUtils.DATE_FORMAT));
        assertEquals("01/08/2020", DateUtils.addDays("01/01/2020", 7, DateUtils.DATE_FORMAT));
        assertEquals("01/09/2020", DateUtils.addDays("01/01/2020", 8, DateUtils.DATE_FORMAT));
        assertEquals("01/10/2020", DateUtils.addDays("01/01/2020", 9, DateUtils.DATE_FORMAT));
        assertEquals("01/11/2020", DateUtils.addDays("01/01/2020", 10, DateUtils.DATE_FORMAT));
        assertEquals("01/12/2020", DateUtils.addDays("01/01/2020", 11, DateUtils.DATE_FORMAT));
        assertEquals("01/13/2020", DateUtils.addDays("01/01/2020", 12, DateUtils.DATE_FORMAT));
        assertEquals("01/14/2020", DateUtils.addDays("01/01/2020", 13, DateUtils.DATE_FORMAT));
        assertEquals("01/15/2020", DateUtils.addDays("01/01/2020", 14, DateUtils.DATE_FORMAT));
        assertEquals("01/16/2020", DateUtils.addDays("01/01/2020", 15, DateUtils.DATE_FORMAT));
        assertEquals("01/17/2020", DateUtils.addDays("01/01/2020", 16, DateUtils.DATE_FORMAT));
        assertEquals("01/18/2020", DateUtils.addDays("01/01/2020", 17, DateUtils.DATE_FORMAT));
        assertEquals("01/18/2020 01:01:01 am", DateUtils.addDays("01/01/2020 01:01:01 am", 17, DateUtils.DATE_TIME_FORMAT_12_HOUR));
    }

    @Test
    public void testIsValidDateTimeString_forNullDateTimeString() {
        assertFalse(DateUtils.isValidDateTimeString(null, null));
    }

    @Test
    public void testIsValidDateTimeString_forEmptyDateTimeString() {
        assertFalse(DateUtils.isValidDateTimeString("", null));
    }

    @Test
    public void testIsValidDateTimeString_forValidDateTimeString() {
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020", null));
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020 01:01:01 am", null));
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020 01:01:01 pm", null));
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020 01:01:01", null));
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020 01:01:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR));
        assertTrue(DateUtils.isValidDateTimeString("01/01/2020 01:01:01 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR));
    }

    @Test
    public void testIsValidDateTimeString_forInvalidDateTimeString() {
        assertFalse(DateUtils.isValidDateTimeString("01/01/2020 01:01:01", DateUtils.DATE_TIME_FORMAT_12_HOUR));
    }

    // test cases for String toString(Date date, String format)
    @Test
    public void testToString_forNullDate() {
        assertNull(DateUtils.toString(null, null));
    }

    @Test
    public void testToString_forValidDate() {
        assertEquals("01/01/2020", DateUtils.toString(DateUtils.getDate("01/01/2020", null), null));
        assertEquals("01/01/2020", DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01 am", null), null));
        assertEquals("01/01/2020 01:01:01 pm", DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR), DateUtils.DATE_TIME_FORMAT_12_HOUR));
        assertEquals("01/01/2020 01:01:01", DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01", DateUtils.DATE_TIME_FORMAT_24_HOUR), DateUtils.DATE_TIME_FORMAT_24_HOUR));
        assertEquals("01/01/2020 01:01:01 am", DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01 am", DateUtils.DATE_TIME_FORMAT_12_HOUR), DateUtils.DATE_TIME_FORMAT_12_HOUR));
        assertEquals("01/01/2020 01:01:01 pm", DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01 pm", DateUtils.DATE_TIME_FORMAT_12_HOUR), DateUtils.DATE_TIME_FORMAT_12_HOUR));
    }

    @Test
    public void testToString_forInvalidDate() {
        assertNull(DateUtils.toString(DateUtils.getDate("01/01/2020 01:01:01", DateUtils.DATE_TIME_FORMAT_12_HOUR), DateUtils.DATE_TIME_FORMAT));
    }

    @Test
    public void testIsValidMilitaryTime_forNullTime() {
        assertFalse(DateUtils.isValidMilitaryTime(null));
    }

    @Test
    public void testIsValidMilitaryTime_forEmptyTime() {
        assertFalse(DateUtils.isValidMilitaryTime(""));
    }

    @Test
    public void testIsValidMilitaryTime_forValidTime() {
        assertTrue(DateUtils.isValidMilitaryTime("00:00:00"));
        assertTrue(DateUtils.isValidMilitaryTime("01:01:01"));
        assertTrue(DateUtils.isValidMilitaryTime("23:59:59"));
    }

    @Test
    public void testIsValidMilitaryTime_forInvalidTime() {
        assertFalse(DateUtils.isValidMilitaryTime("24:00:00"));
        assertFalse(DateUtils.isValidMilitaryTime("00:60:00"));
        assertFalse(DateUtils.isValidMilitaryTime("00:00:60"));
        assertFalse(DateUtils.isValidMilitaryTime("00:00:00 am"));
        assertFalse(DateUtils.isValidMilitaryTime("00:00:00 pm"));
    }

    @Test
    public void testIsBetween_forNullDate() {
        assertFalse(DateUtils.isBetween(null, null, null));
    }

    @Test
    public void testIsBetween_forNullStartDate() {
        assertFalse(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), null, DateUtils.getDate("01/01/2020", null)));
    }

    @Test
    public void testIsBetween_forNullEndDate() {
        assertFalse(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), null));
    }

    @Test
    public void testIsBetween_forValidDate() {
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/02/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/01/2020", null), DateUtils.getDate("01/02/2020", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020    01:01:01 am", null), DateUtils.getDate("01/01/2020    01:01:01 am", null), DateUtils.getDate("01/02/2020    01:01:01 am", null)));
        assertTrue(DateUtils.isBetween(DateUtils.getDate("01/01/2020    01:01:01 pm", null), DateUtils.getDate("01/01/2020    01:01:01 pm", null), DateUtils.getDate("01/02/2020    01:01:01 pm", null)));
    }

}
