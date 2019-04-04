package com.foxminded.domain;

import lombok.Data;

@Data
public class Subject {

    private long id;
    private String name;

    public Subject(String name){
        this.name = name;
    }
}
