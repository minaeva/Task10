package com.foxminded.model;

import lombok.Data;

@Data
public class StudentCard {

    private long id;
    private String name;

    public StudentCard(String name){
        this.name = name;
    }
}
