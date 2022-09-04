package com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate;

import com.zerobase.publicwifiservice.repository.jdbc.ZeroBaseJdbcBase;
import com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.exception.JdbcErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ZeroBaseJdbcTemplate extends ZeroBaseJdbcBase {
    private <T> T jdbcContextSelectTemplate(final JdbcStrategy jdbcStrategy, final ResultSetExtractor<T> rse) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = jdbcStrategy.createPreparedStatement(con);
            rs = pstmt.executeQuery();
            return rse.extractData(rs);
        } catch (SQLException e) {
            rollBack(con);
            throw new JdbcErrorException(e);
        } finally {
            closeResultSet(rs);
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public <T> List<T> executeSelectQuery(String query, RowMapper<T> rowMapper) {
        return jdbcContextSelectTemplate(connection -> connection.prepareStatement(query),
                new RowMapperResultSetExtractor<>(rowMapper));
    }

    private <T> int jdbcContextBatchSaveTemplate(final JdbcStrategy jdbcStrategy, final JdbcSaveStrategy batchStrategy, final List<T> list) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = jdbcStrategy.createPreparedStatement(con);

            int count = 0;
            for (T item : list) {
                batchStrategy.setPreparedStatement(pstmt, item);
                pstmt.addBatch();
                count++;

                if (count % info.getBatchSize() == 0) {
                    pstmt.executeBatch();
                }
            }
            if (count > 0) {
                pstmt.executeBatch();
            }

            con.commit();
            return list.size();
        } catch (SQLException e) {
            rollBack(con);
            throw new JdbcErrorException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public <T> int executeBatchSaveQuery(String query, JdbcSaveStrategy batchStrategy, List<T> list) {
        return jdbcContextBatchSaveTemplate(connection -> connection.prepareStatement(query), batchStrategy, list);
    }

    private <T> int jdbcContextSaveTemplate(final JdbcStrategy jdbcStrategy, final JdbcSaveStrategy batchStrategy, final T item) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = jdbcStrategy.createPreparedStatement(con);
            batchStrategy.setPreparedStatement(pstmt, item);
            pstmt.executeUpdate();
            con.commit();
            return 1;
        } catch (SQLException e) {
            rollBack(con);
            throw new JdbcErrorException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public <T> int executeSaveQuery(String query, JdbcSaveStrategy batchStrategy, T item) {
        return jdbcContextSaveTemplate(connection -> connection.prepareStatement(query), batchStrategy, item);
    }

    private <T> int jdbcContextDefaultTemplate(final JdbcStrategy jdbcStrategy, Object... parameters) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            pstmt = jdbcStrategy.createPreparedStatement(con);

            for (int i = 1; i <= parameters.length; i++) {
                pstmt.setObject(i, parameters[i - 1]);
            }
            int resultCount = pstmt.executeUpdate();

            con.commit();
            return resultCount;
        } catch (SQLException e) {
            rollBack(con);
            throw new JdbcErrorException(e);
        } finally {
            closeStatement(pstmt);
            closeConnection(con);
        }
    }

    public <T> int executeDeleteQuery(String query) {
        return jdbcContextDefaultTemplate(connection -> connection.prepareStatement(query));
    }

    public <T> int executeDeleteQuery(String query, Object... parameters) {
        return jdbcContextDefaultTemplate(connection -> connection.prepareStatement(query), parameters);
    }
}