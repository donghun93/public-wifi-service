package com.zerobase.publicwifiservice.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WifiNearInfo {
    private final Double lat;
    private final Double lnt;
    private final int limit;

    @Builder
    private WifiNearInfo(Double lat, Double lnt, int limit) {
        this.lat = lat;
        this.lnt = lnt;
        this.limit = limit;
    }
}
