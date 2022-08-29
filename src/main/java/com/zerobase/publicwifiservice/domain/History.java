package com.zerobase.publicwifiservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class History {
    private final Long id;
    private final Double xLocation;
    private final Double yLocation;
    private final LocalDateTime searchDate;

    @Builder
    private History(Long id, Double xLocation, Double yLocation, LocalDateTime searchDate) {
        this.id = id;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.searchDate = searchDate;
    }
}
