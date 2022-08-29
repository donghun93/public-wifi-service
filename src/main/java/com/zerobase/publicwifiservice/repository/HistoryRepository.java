package com.zerobase.publicwifiservice.repository;

import com.zerobase.publicwifiservice.domain.History;

import java.util.List;

public interface HistoryRepository {
    void delete(Long deleteId);
    void save(History history);
    List<History> findAll();
}
