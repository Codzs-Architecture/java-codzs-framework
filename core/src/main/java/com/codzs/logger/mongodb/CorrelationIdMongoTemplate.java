package com.codzs.logger.mongodb;

/**
 * MongoDB correlation ID support utilities.
 * 
 * This package contains utilities for MongoDB correlation ID injection.
 * To use these utilities, services must:
 * 
 * 1. Include spring-boot-starter-data-mongodb dependency
 * 2. Copy and adapt the CorrelationIdMongoEventListener from authorization service
 * 3. Ensure entities implement CorrelationIdAware or have correlationId field
 * 
 * Example usage:
 * 
 * ```java
 * // In your service's configuration
 * @EventListener
 * public class YourCorrelationIdMongoEventListener extends AbstractMongoEventListener<Object> {
 *     // Implementation similar to authorization service
 * }
 * ```
 * 
 * @author Codzs Team
 * @since 1.0
 */
public final class CorrelationIdMongoTemplate {

    private CorrelationIdMongoTemplate() {
        // Utility class
    }

    /**
     * Interface for entities that support correlation ID.
     * Entities can implement this interface for explicit correlation ID support.
     */
    public interface CorrelationIdAware {
        String getCorrelationId();
        void setCorrelationId(String correlationId);
    }
    
    /**
     * Utility method to inject correlation ID into an entity via reflection.
     * 
     * @param entity the entity to inject correlation ID into
     * @param correlationId the correlation ID to inject
     * @return true if injection was successful, false otherwise
     */
    public static boolean injectCorrelationId(Object entity, String correlationId) {
        if (entity == null || correlationId == null) {
            return false;
        }
        
        // Try interface first
        if (entity instanceof CorrelationIdAware) {
            ((CorrelationIdAware) entity).setCorrelationId(correlationId);
            return true;
        }
        
        // Try reflection
        try {
            var field = entity.getClass().getDeclaredField("correlationId");
            field.setAccessible(true);
            field.set(entity, correlationId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Utility method to extract correlation ID from an entity via reflection.
     * 
     * @param entity the entity to extract correlation ID from
     * @return the correlation ID or null if not found
     */
    public static String extractCorrelationId(Object entity) {
        if (entity == null) {
            return null;
        }
        
        // Try interface first
        if (entity instanceof CorrelationIdAware) {
            return ((CorrelationIdAware) entity).getCorrelationId();
        }
        
        // Try reflection
        try {
            var field = entity.getClass().getDeclaredField("correlationId");
            field.setAccessible(true);
            return (String) field.get(entity);
        } catch (Exception e) {
            return null;
        }
    }
}