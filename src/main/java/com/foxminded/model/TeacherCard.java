package com.foxminded.model;

import lombok.Data;

@Data
public class TeacherCard {

    private long id;
    private String name;
    private Subject subject;

    public TeacherCard(String name){
        this.name = name;
    }
}
