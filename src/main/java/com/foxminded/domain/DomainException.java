package com.foxminded.dao;

public class DomainException extends RuntimeException {

    public DomainException(){
        super();
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
