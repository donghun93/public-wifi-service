package com.zerobase.publicwifiservice.repository.jdbc.jdbctemplate.exception;

public class JdbcErrorException extends RuntimeException {

    public JdbcErrorException() {
        super();
    }

    public JdbcErrorException(String message) {
        super(message);
    }

    public JdbcErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcErrorException(Throwable cause) {
        super(cause);
    }
}
