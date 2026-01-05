package com.codzs.utility;

import com.codzs.utility.StringUtils;
import com.codzs.utility.TimezoneUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimezoneUtilsTest {
    @Test
    public void testGetDefaultTimezone() {
        String result = TimezoneUtils.getDefaultTimezone();
        assertTrue(StringUtils.isNotEmpty(result));
    }
}
