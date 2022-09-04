package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.repository.HistoryRepository;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.JdbcSaveStrategy;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.RowMapper;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.ZeroBaseJdbcTemplate;
import lombok.RequiredArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class ZeroBaseJdbcHistoryRepository implements HistoryRepository {

    private final ZeroBaseJdbcTemplate zeroBaseJdbcTemplate;

    @Override
    public int delete(Long deleteId) {
        return zeroBaseJdbcTemplate.executeDeleteQuery("delete from history where history_id = ?", deleteId);
    }

    @Override
    public int save(History history) {
        String insertQuery = "insert into " +
                " history(x_location, y_location, search_date) " +
                " values(?,?,now())";

        return zeroBaseJdbcTemplate.executeSaveQuery(insertQuery, getJdbcSaveStrategy(), history);
    }

    @Override
    public List<History> findAll() {
        return zeroBaseJdbcTemplate.executeSelectQuery("select * from history order by history_id desc", historyRowMapper());
    }

    private static JdbcSaveStrategy getJdbcSaveStrategy() {
        return new JdbcSaveStrategy() {
            @Override
            public <T> void setPreparedStatement(PreparedStatement pstmt, T item) throws SQLException {
                History history = (History) item;
                pstmt.setDouble(1, history.getXLocation());
                pstmt.setDouble(2, history.getYLocation());
            }
        };
    }

    private RowMapper<History> historyRowMapper() {
        return (rs, rowNum) ->
            History.builder()
                    .id(rs.getLong("history_id"))
                    .xLocation(rs.getDouble("x_location"))
                    .yLocation(rs.getDouble("y_location"))
                    .searchDate(rs.getTimestamp("search_date").toLocalDateTime())
                    .build();
    }
}
