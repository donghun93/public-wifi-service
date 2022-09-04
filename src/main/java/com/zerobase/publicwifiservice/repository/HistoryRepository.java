package com.zerobase.publicwifiservice.repository;

import com.zerobase.publicwifiservice.domain.History;

import java.util.List;

public interface HistoryRepository {
    int delete(Long deleteId);
    int save(History history);
    List<History> findAll();
}
