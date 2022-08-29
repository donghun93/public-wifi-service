package com.zerobase.publicwifiservice.controller;

import com.zerobase.publicwifiservice.dto.WifiNearInfoRequest;
import com.zerobase.publicwifiservice.service.HistoryService;
import com.zerobase.publicwifiservice.service.WifiService;
import com.zerobase.servlet.mvc.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WifiController {

    private final WifiService wifiService;
    private final HistoryService historyService;

    public String wifiLoadAndSave(Model model) {
        model.addAttribute("loadWifiResult", wifiService.loadWifiAndSave());
        return "/wifi/load-wifi";
    }

    public String wifiSearchNearInfo(Model model, final WifiNearInfoRequest wifiNearInfoRequest) {
        model.addAttribute("nearWifiList", wifiService.getWifiSearchNearInfo(wifiNearInfoRequest));
        return "index";
    }

    public String getWifiHistory(Model model) {
        model.addAttribute("historyList", historyService.getWifiHistory());
        return "/wifi/history";
    }

    public String deleteHistory(final Long deleteId) {
        historyService.deleteHistory(deleteId);
        return "/wifi/history";
    }
}
