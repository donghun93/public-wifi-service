package com.zerobase.servlet.manager;

import com.zerobase.api.config.WifiApiConfig;
import com.zerobase.api.config.WifiApiInfo;
import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.config.JdbcConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    public void init() {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");

        try {
            if (inputStream != null) {
                properties.load(inputStream);
                dataBasePropertiesInit(properties);
                apiPropertiesInit(properties);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void dataBasePropertiesInit(Properties properties) {

        ConnectionInfo connectionInfo = ConnectionInfo.builder()
                .driverName(properties.getProperty("db.driver"))
                .url(properties.getProperty("db.url"))
                .user(properties.getProperty("db.username"))
                .pass(properties.getProperty("db.password"))
                .batchSize(Integer.parseInt(properties.getProperty("db.batch_size")))
                .build();

        JdbcConfig.getInstance().init(connectionInfo);
    }

    private void apiPropertiesInit(Properties properties) {
        WifiApiInfo apiInfo = WifiApiInfo.builder()
                .domain(properties.getProperty("api.domain"))
                .authenticationKey(properties.getProperty("api.authentication_key"))
                .serviceName(properties.getProperty("api.service_name"))
                .maxRequestCount(Integer.parseInt(properties.getProperty("api.max_request_count")))
                .build();
        WifiApiConfig.getInstance().init(apiInfo);
    }
}
