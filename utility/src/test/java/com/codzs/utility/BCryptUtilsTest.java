package com.codzs.utility;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class BCryptUtilsTest {
    @Test
    public void testIsEmpty_forEmptyMap() {
        assertNotNull(BCryptUtils.encode("secret1"));
    }

}
