package com.codzs.constant;

/**
 * Constants for Organization module.
 * Contains all static values, default configurations, and business rules
 * related to organization management.
 * 
 * NOTE: For correlation ID header, use LoggerConstant.CORRELATION_ID_HEADER
 * 
 * @author Codzs Team
 * @since 1.0
 */
public final class HeaderConstant {

    private HeaderConstant() {
        // Utility class - prevent instantiation
    }

    // ========== HTTP Headers ==========
    public static final String HEADER_ORGANIZATION_ID = "X-Organization-Id";
    public static final String HEADER_TENANT_ID = "X-Tenant";
    public static final String HEADER_USER_ID = "X-User-Id";
    public static final String USER_AGENT = "User-Agent";
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";
    public static final String X_REAL_IP = "X-Real-IP";
}