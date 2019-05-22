package com.foxminded.domain;

public class DomainNotFoundException extends RuntimeException {

    public DomainNotFoundException(){
        super();
    }

    public DomainNotFoundException(String message) {
        super(message);
    }

    public DomainNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

