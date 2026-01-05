package com.codzs.platform.service;

import com.codzs.platform.constant.Platform;
import org.springframework.stereotype.Component;

@Component
public class PlatformService {
    private Platform platform;

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
