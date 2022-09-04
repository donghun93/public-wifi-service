package com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcStrategy {
    PreparedStatement createPreparedStatement(Connection connection) throws SQLException;
}
