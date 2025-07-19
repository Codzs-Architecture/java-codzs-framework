package com.codzs.security.constant;

import java.util.regex.Pattern;

/**
 * Constants for IP address validation and security operations.
 * Provides compiled regex patterns for IPv4 address and CIDR range validation.
 * 
 * @author Nitin Khaitan
 * @since 1.2
 */
public class IPConstant {
    
    /**
     * Regular expression pattern for validating IPv4 addresses.
     * Matches valid IPv4 addresses in the format xxx.xxx.xxx.xxx 
     * where each octet is 0-255.
     */
    public static final Pattern IPV4_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    
    /**
     * Regular expression pattern for validating CIDR notation.
     * Matches valid CIDR ranges in the format xxx.xxx.xxx.xxx/yy
     * where the IP part is a valid IPv4 address and the prefix length is 0-32.
     */
    public static final Pattern CIDR_PATTERN = Pattern.compile(
        "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)/([0-9]|[1-2][0-9]|3[0-2])$");
    
    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private IPConstant() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}