package com.zerobase.servlet.manager;

public abstract class WifiUriMapper {
    private static final String DEFAULT_URI = "/wifi";
    private static final String HISTORY = ".history";
    private static final String LOAD = ".load";
    private static final String NEAR = ".near";
    private static final String DELETE_HISTORY = ".history.delete";
    private static final String ACTION = ".do";


    public static String getWifiHistoryURI() {
        return DEFAULT_URI + HISTORY + ACTION;
    }

    public static String getWifiLoadURI() {
        return DEFAULT_URI + LOAD + ACTION;
    }

    public static String getWifiNearURI() {
        return DEFAULT_URI + NEAR + ACTION;
    }

    public static String getDeleteHistory() {
        return DEFAULT_URI + DELETE_HISTORY + ACTION;
    }
}
