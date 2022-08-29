package com.zerobase.publicwifiservice.dto;

import com.zerobase.publicwifiservice.domain.History;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class HistoryInfoResponse {

    private Long id;
    private Double xlocation;
    private Double ylocation;
    private String searchDate;

    @Builder
    private HistoryInfoResponse(Long id, Double xlocation, Double ylocation, String searchDate) {
        this.id = id;
        this.xlocation = xlocation;
        this.ylocation = ylocation;
        this.searchDate = searchDate;
    }

    public static HistoryInfoResponse toResponse(History history) {
        return HistoryInfoResponse.builder()
                .id(history.getId())
                .xlocation(history.getXLocation())
                .ylocation(history.getYLocation())
                .searchDate(history.getSearchDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
