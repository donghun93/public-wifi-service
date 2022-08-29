package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.domain.History;
import com.zerobase.publicwifiservice.repository.HistoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHistoryRepository extends JdbcBase implements HistoryRepository {
    public JdbcHistoryRepository(ConnectionInfo info) {
        super(info);
    }

    @Override
    public void delete(Long deleteId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String deleteQuery = "delete from " +
                " history " +
                " where history_id = ?";

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setLong(1, deleteId);
            int resultCnt = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollBack(conn);
            throw new RuntimeException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

    @Override
    public void save(History history) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String insertQuery = "insert into " +
                " history(x_location, y_location, search_date) " +
                " values(?,?,now())";

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(insertQuery);
            pstmt.setDouble(1, history.getXLocation());
            pstmt.setDouble(2, history.getYLocation());
            int resultCnt = pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            rollBack(conn);
            throw new RuntimeException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(conn);
        }
    }

    @Override
    public List<History> findAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "select * " +
                " from history " +
                " order by history_id desc";

        List<History> histories = new ArrayList<>();
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                histories.add(History.builder()
                        .id(rs.getLong("history_id"))
                        .xLocation(rs.getDouble("x_location"))
                        .yLocation(rs.getDouble("y_location"))
                        .searchDate(rs.getTimestamp("search_date").toLocalDateTime())
                        .build());
            }

            return histories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }
}
