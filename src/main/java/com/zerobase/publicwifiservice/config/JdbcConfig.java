package com.zerobase.publicwifiservice.config;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import lombok.Getter;

@Getter
public class JdbcConfig {
    private static volatile JdbcConfig instance = null;
    public static JdbcConfig getInstance() {
        if(instance == null) {
            synchronized (JdbcConfig.class) {
                if(instance == null) {
                    instance = new JdbcConfig();
                }
            }
        }

        return instance;
    }

    private ConnectionInfo info;

    public void init(ConnectionInfo info) {
        this.info = info;
    }
}
