package com.zerobase.servlet.config;

import com.zerobase.api.WifiApiService;
import com.zerobase.api.WifiApiServiceImpl;
import com.zerobase.api.parser.okhttp3.GsonWifiApiMessageParser;
import com.zerobase.api.processor.okhttp3.OkHttp3WifiApiProcessor;
import com.zerobase.publicwifiservice.controller.WifiController;
import com.zerobase.publicwifiservice.repository.HistoryRepository;
import com.zerobase.publicwifiservice.repository.WifiRepository;
import com.zerobase.publicwifiservice.repository.jdbc.ZeroBaseJdbcHistoryRepository;
import com.zerobase.publicwifiservice.repository.jdbc.ZeroBaseJdbcWifiRepository;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import com.zerobase.publicwifiservice.repository.jdbc.old.ZeroBaseJdbcOldHistoryRepository;
import com.zerobase.publicwifiservice.repository.jdbc.old.ZeroBaseJdbcOldWifiRepository;
import com.zerobase.publicwifiservice.service.HistoryService;
import com.zerobase.publicwifiservice.service.WifiService;

public class AppConfig {
    private static volatile AppConfig instance = null;
    private WifiRepository wifiRepository;
    private HistoryRepository historyRepository;
    private ZeroBaseJdbcTemplate zeroBaseJdbcTemplate;

    public static AppConfig getInstance() {
        if (instance == null) {
            synchronized (AppConfig.class) {
                if (instance == null) {
                    instance = new AppConfig();
                }
            }
        }
        return instance;
    }


    public WifiController wifiController() {
        return new WifiController(
                wifiService(),
                historyService());
    }

    private HistoryService historyService() {
        return new HistoryService(historyRepository());
    }

    private WifiService wifiService() {
        return new WifiService(
                wifiApiService(),
                wifiRepository(),
                historyRepository());
    }

    private WifiApiService wifiApiService() {
        return new WifiApiServiceImpl(
                new OkHttp3WifiApiProcessor(),
                new GsonWifiApiMessageParser()
        );
    }

    private WifiRepository wifiRepository() {
        if (wifiRepository == null) {
            // wifiRepository = new ZeroBaseJdbcOldWifiRepository();
            wifiRepository = new ZeroBaseJdbcWifiRepository(zeroBaseJdbcTemplate());
        }
        return wifiRepository;
    }

    private HistoryRepository historyRepository() {
        if (historyRepository == null) {
            // historyRepository = new ZeroBaseJdbcOldHistoryRepository();
            historyRepository = new ZeroBaseJdbcHistoryRepository(zeroBaseJdbcTemplate());
        }
        return historyRepository;
    }

    private ZeroBaseJdbcTemplate zeroBaseJdbcTemplate() {
        if (zeroBaseJdbcTemplate == null) {
            zeroBaseJdbcTemplate = new ZeroBaseJdbcTemplate();
        }
        return zeroBaseJdbcTemplate;
    }
}
