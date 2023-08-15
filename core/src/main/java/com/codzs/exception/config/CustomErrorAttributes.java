package com.codzs.exception.config;

import com.codzs.exception.constant.ErrorAttributeConstant;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        errorAttributes.put(ErrorAttributeConstant.LOCALE, webRequest.getLocale().toString());
        errorAttributes.put(ErrorAttributeConstant.TIMESTAMP, String.valueOf(errorAttributes.get(ErrorAttributeConstant.TIMESTAMP)));
        errorAttributes.put(ErrorAttributeConstant.STATUS, String.valueOf(errorAttributes.get(ErrorAttributeConstant.STATUS)));

        errorAttributes.remove(ErrorAttributeConstant.ERROR);
        errorAttributes.put(ErrorAttributeConstant.CAUSE, errorAttributes.get(ErrorAttributeConstant.MESSAGE));
        errorAttributes.remove(ErrorAttributeConstant.MESSAGE);

        return errorAttributes;
    }
}
