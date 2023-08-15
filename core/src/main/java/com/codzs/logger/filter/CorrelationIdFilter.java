package com.codzs.logger.filter;

import com.codzs.logger.constant.LoggerConstant;
import com.codzs.logger.generator.CorrelationIdGenerator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CorrelationIdFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String correlationId = request.getHeader(LoggerConstant.CORRELATION_ID_HEADER);

        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = CorrelationIdGenerator.generateCorrelationId();
        }
        MDC.put(LoggerConstant.CORRELATION_ID, correlationId); // Set correlation ID in MDC for logging
        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(LoggerConstant.CORRELATION_ID);
        }
    }
}
