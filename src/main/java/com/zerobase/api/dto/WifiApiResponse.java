package com.zerobase.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class WifiApiResponse {

    private final Long totalCount;
    private final WifiApiResultResponse result;
    private final List<WifiApiIntoDetailResponse> details;

    @Builder
    private WifiApiResponse(Long totalCount, WifiApiResultResponse result, List<WifiApiIntoDetailResponse> details) {
        this.totalCount = totalCount;
        this.result = result;
        this.details = details;
    }
}
