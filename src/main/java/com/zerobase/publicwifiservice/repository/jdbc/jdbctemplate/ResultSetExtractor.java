package com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetExtractor<T> {
	T extractData(ResultSet rs) throws SQLException;
}