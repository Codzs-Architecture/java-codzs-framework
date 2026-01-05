package com.codzs.logger.generator;

import com.codzs.logger.constant.LoggerConstant;
import java.util.UUID;

/**
 * Utility class for generating unique correlation IDs.
 * Provides methods for creating correlation IDs with different patterns
 * suitable for distributed tracing and request correlation.
 * 
 * @author Codzs Team
 * @since 1.0
 */
public final class CorrelationIdGenerator {

    private CorrelationIdGenerator() {
        // Utility class - prevent instantiation
    }

    /**
     * Generates a simple UUID-based correlation ID.
     * 
     * @return UUID string as correlation ID
     */
    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates an enhanced correlation ID with timestamp and shortened UUID.
     * Format: req_<timestamp>_<uuid_suffix>
     * This format provides better sorting and readability in logs.
     * 
     * @return enhanced correlation ID with timestamp
     */
    public static String generateEnhancedCorrelationId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        long timestamp = System.currentTimeMillis();
        return LoggerConstant.CORRELATION_ID_PREFIX + timestamp + "_" + uuid.substring(0, 8);
    }

    /**
     * Creates a child correlation ID from a parent correlation ID.
     * Used for tracking nested operations within a request flow.
     * 
     * @param parentCorrelationId the parent correlation ID
     * @param operation the operation name for the child
     * @return child correlation ID that includes parent context
     */
    public static String generateChildCorrelationId(String parentCorrelationId, String operation) {
        String childSuffix = operation + "_" + System.currentTimeMillis();
        
        if (parentCorrelationId != null && !parentCorrelationId.trim().isEmpty()) {
            return parentCorrelationId + "_" + childSuffix;
        } else {
            return LoggerConstant.CORRELATION_ID_PREFIX + childSuffix;
        }
    }
}
