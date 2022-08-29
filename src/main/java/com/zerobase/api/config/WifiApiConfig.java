package com.zerobase.api.config;

import lombok.Getter;

@Getter
public class WifiApiConfig {

    private static volatile WifiApiConfig instance = null;

    public static WifiApiConfig getInstance() {
        if(instance == null) {
            synchronized (WifiApiConfig.class) {
                if(instance == null) {
                    instance = new WifiApiConfig();
                }
            }
        }

        return instance;
    }

    private WifiApiInfo info;

    public void init(WifiApiInfo info) {
        this.info = info;
    }
}
