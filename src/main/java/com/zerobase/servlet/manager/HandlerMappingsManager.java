package com.zerobase.servlet.manager;

import com.zerobase.publicwifiservice.controller.WifiController;
import com.zerobase.servlet.config.AppConfig;

import java.util.HashMap;
import java.util.Map;

public class HandlerMappingsManager {
    public Map<String, Object> createHandlerMappings() {
        Map<String, Object> handlers = new HashMap<>();
        initWifiHandler(handlers);

        return handlers;
    }

    private void initWifiHandler(Map<String, Object> handlers) {
        WifiController controller = AppConfig.getInstance().wifiController();
        handlers.put(WifiUriMapper.getWifiHistoryURI(), controller);
        handlers.put(WifiUriMapper.getWifiLoadURI(), controller);
        handlers.put(WifiUriMapper.getWifiNearURI(), controller);
        handlers.put(WifiUriMapper.getDeleteHistory(), controller);
    }

}
