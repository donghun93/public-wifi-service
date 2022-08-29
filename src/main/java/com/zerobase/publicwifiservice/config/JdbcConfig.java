package com.zerobase.publicwifiservice.config;

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
