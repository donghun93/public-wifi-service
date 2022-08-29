package com.zerobase.publicwifiservice.service;

import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.dto.HistoryInfoResponse;
import com.zerobase.publicwifiservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;

    public List<HistoryInfoResponse> getWifiHistory() {
        return historyRepository.findAll().stream()
                .map(HistoryInfoResponse::toResponse)
                .collect(Collectors.toList());
    }

    public void deleteHistory(Long historyId) {
        historyRepository.delete(historyId);
    }
}
