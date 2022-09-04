package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.config.JdbcConfig;
import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZeroBaseJdbcHistoryRepositoryTest {

    ZeroBaseJdbcHistoryRepository zeroBaseJdbcHistoryRepository;

    @BeforeEach
    void before() {
        ConnectionInfo connectionInfo = ConnectionInfo.builder()
                .batchSize(1000)
                .driverName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/wifi_db")
                .user("wifi")
                .pass("wifi1234")
                .build();
        JdbcConfig.getInstance().init(connectionInfo);

        zeroBaseJdbcHistoryRepository = new ZeroBaseJdbcHistoryRepository(new ZeroBaseJdbcTemplate());
    }

    @DisplayName("히스토리 삭제 테스트")
    @Test
    void historyDeleteTest() {
        // given
        Long deleteId = 98L;

        // when
        int deletedCount = zeroBaseJdbcHistoryRepository.delete(deleteId);

        // then
    }

    @DisplayName("히스토리 저장 테스트")
    @Test
    void historySaveTest() {
        // given
        History history = History.builder()
                .yLocation(127.001)
                .xLocation(37.022)
                .build();

        // when
        int savedCount = zeroBaseJdbcHistoryRepository.save(history);

        // then
        assertEquals(1, savedCount);
    }
}