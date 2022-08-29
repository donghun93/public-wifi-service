package com.zerobase.publicwifiservice.config;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ConnectionInfo {
    private final String driverName;
    private final String url;
    private final String user;
    private final String pass;
    private final int batchSize;

    @Builder
    private ConnectionInfo(String driverName, String url, String user, String pass, int batchSize) {
        this.driverName = driverName;
        this.url = url;
        this.user = user;
        this.pass = pass;
        this.batchSize = batchSize;
    }
}
