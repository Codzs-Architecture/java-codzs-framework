package com.codzs.logger.constant;

/**
 * Constants for logging functionality across the framework.
 * Contains all logging-related constants including MDC keys, 
 * HTTP headers, and common values.
 * 
 * @author Codzs Team
 * @since 1.0
 */
public final class LoggerConstant {

    private LoggerConstant() {
        // Utility class - prevent instantiation
    }

    // ========== MDC Keys ==========
    public static final String CORRELATION_ID = "correlationId";
    public static final String USER_ID = "userId";
    
    // ========== HTTP Headers ==========
    public static final String CORRELATION_ID_HEADER = "X-Correlation-ID";
    
    // ========== Common Values ==========
    public static final String ANONYMOUS = "anonymous";
    public static final String ANONYMOUS_USER = "anonymousUser";
    
    // ========== Correlation ID Patterns ==========
    public static final String CORRELATION_ID_PREFIX = "req_";
}
