package com.zerobase.publicwifiservice.repository.jdbc;

import com.zerobase.publicwifiservice.config.ConnectionInfo;
import com.zerobase.publicwifiservice.config.JdbcConfig;
import lombok.RequiredArgsConstructor;

import java.sql.*;

@RequiredArgsConstructor
public abstract class JdbcBase {
    protected ConnectionInfo info;

    protected Connection getConnection() throws SQLException {
        try {
            if(info == null) {
                info = JdbcConfig.getInstance().getInfo();
            }
            Class.forName(info.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(info.getUrl(), info.getUser(), info.getPass());
    }

    protected void rollBack(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeResultSet(ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeStatement(Statement st) {
        try {
            if (st != null && !st.isClosed()) {
                st.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
