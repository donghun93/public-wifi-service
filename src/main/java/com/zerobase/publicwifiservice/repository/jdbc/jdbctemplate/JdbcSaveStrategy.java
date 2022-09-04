package com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcSaveStrategy {
    <T> void setPreparedStatement(PreparedStatement pstmt, T item) throws SQLException;
}
