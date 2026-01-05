package com.codzs.logger.interceptor;

import com.codzs.logger.constant.LoggerConstant;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CorrelationIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String correlationId = MDC.get(LoggerConstant.CORRELATION_ID); // Retrieve correlation ID from MDC
        HttpHeaders headers = request.getHeaders();
        headers.add(LoggerConstant.CORRELATION_ID_HEADER, correlationId); // Add correlation ID to request headers
        return execution.execute(request, body);
    }
}
