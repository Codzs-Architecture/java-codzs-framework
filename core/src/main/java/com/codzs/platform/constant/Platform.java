package com.codzs.platform.constant;

public enum Platform {
    AWS_EC2_INSTANCE("AWS_EC2_INSTANCE"),
    AWS_LAMBDA("AWS_LAMBDA");

    private final String platform;

    Platform(String platform) {
        this.platform = platform;
    }
}
