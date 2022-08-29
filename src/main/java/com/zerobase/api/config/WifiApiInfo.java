package com.zerobase.api.config;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WifiApiInfo {
    private final String domain;
    private final String authenticationKey;
    private final String serviceName;
    private final int maxRequestCount;

    @Builder
    private WifiApiInfo(String domain, String authenticationKey, String serviceName, int maxRequestCount) {
        this.domain = domain;
        this.authenticationKey = authenticationKey;
        this.serviceName = serviceName;
        this.maxRequestCount = maxRequestCount;
    }
}
