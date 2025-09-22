package com.codzs.exception.config;

import com.codzs.exception.constant.ErrorAttributeConstant;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorAttributes implements HandlerExceptionResolver, Ordered {
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
                                       Object handler, Exception ex) {
        
        Map<String, Object> errorAttributes = buildErrorAttributes(request, response, ex);
        
        // Set attributes in request for error page rendering
        for (Map.Entry<String, Object> entry : errorAttributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        
        // Return null to allow other error handlers to process
        return null;
    }
    
    public Map<String, Object> buildErrorAttributes(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        
        // Get basic error information
        Integer status = response.getStatus();
        String message = ex != null ? ex.getMessage() : "Internal Server Error";
        String path = request.getRequestURI();
        
        // Set default values if not found
        if (status == null || status == 200) {
            status = 500;
        }
        if (message == null) {
            message = "Internal Server Error";
        }
        
        // Build custom error attributes following original structure
        errorAttributes.put(ErrorAttributeConstant.LOCALE, request.getLocale().toString());
        errorAttributes.put(ErrorAttributeConstant.TIMESTAMP, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        errorAttributes.put(ErrorAttributeConstant.STATUS, String.valueOf(status));
        errorAttributes.put(ErrorAttributeConstant.CAUSE, message);
        
        if (path != null) {
            errorAttributes.put("path", path);
        }
        
        // Include exception details if available
        if (ex != null) {
            errorAttributes.put("exception", ex.getClass().getName());
        }
        
        return errorAttributes;
    }
    
    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
