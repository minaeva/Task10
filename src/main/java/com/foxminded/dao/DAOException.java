package com.foxminded.dao;

public class DAOException extends RuntimeException {

    DAOException(){
        super();
    }

    DAOException(String message) {
        super(message);
    }

    DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
