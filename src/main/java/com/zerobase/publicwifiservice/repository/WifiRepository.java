package com.zerobase.publicwifiservice.repository;

import com.zerobase.publicwifiservice.domain.Wifi;
import com.zerobase.publicwifiservice.domain.WifiNearInfo;

import java.util.List;

public interface WifiRepository {
    List<Wifi> findNearWifiInfo(WifiNearInfo wifiNearInfo);
    void saveAll(List<Wifi> wifiList);
    void deleteAll();
}
