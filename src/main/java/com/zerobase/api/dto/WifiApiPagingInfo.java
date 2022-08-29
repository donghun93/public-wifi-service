package com.zerobase.api.dto;

import com.zerobase.api.config.WifiApiConfig;
import lombok.Builder;
import lombok.Getter;

@Getter
public class WifiApiPagingInfo {
    private final int totalCount;
    private final int queryCount;
    private final int maxRequestCount;

    @Builder
    private WifiApiPagingInfo(int totalCount, int queryCount, int maxRequestCount) {
        this.totalCount = totalCount;
        this.queryCount = queryCount;
        this.maxRequestCount = WifiApiConfig.getInstance().getInfo().getMaxRequestCount();
    }
}
