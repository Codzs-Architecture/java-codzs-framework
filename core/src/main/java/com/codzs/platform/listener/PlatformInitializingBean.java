package com.codzs.platform.listener;

import com.codzs.platform.constant.Platform;
import com.codzs.platform.service.PlatformService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlatformInitializingBean implements InitializingBean {
    @Autowired
    private PlatformService platformService;

    @Value("${platform.type}")
    private String platformType;

    @Override
    public void afterPropertiesSet() {
        platformService.setPlatform(Platform.valueOf(platformType));
    }
}
