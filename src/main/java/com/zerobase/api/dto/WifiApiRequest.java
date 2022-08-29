package com.zerobase.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WifiApiRequest {
    private final int start;
    private final int end;
    private final WifiLoadRequestFileType requestFileType;

    @Builder
    private WifiApiRequest(int start, int end, WifiLoadRequestFileType requestFileType) {
        this.start = start;
        this.end = end;
        this.requestFileType = requestFileType;
    }
}
