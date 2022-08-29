package com.zerobase.publicwifiservice.dto;

import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.domain.WifiNearInfo;
import lombok.Builder;
import lombok.Data;

@Data
public class WifiNearInfoRequest {
    Double lat;
    Double lnt;

    @Builder
    private WifiNearInfoRequest(Double lat, Double lnt) {
        this.lat = lat;
        this.lnt = lnt;
    }

    public History toHistoryEntity() {
        return History.builder()
                .xLocation(this.lat)
                .yLocation(this.lnt)
                .build();
    }

    public WifiNearInfo toWifiNearInfo(int limit) {
        return WifiNearInfo.builder()
                .lat(this.lat)
                .lnt(this.lnt)
                .limit(limit)
                .build();
    }
}
