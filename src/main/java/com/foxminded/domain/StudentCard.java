package com.foxminded.domain;

import lombok.Data;

@Data
public class StudentCard {

    private String name;
    private String groupName;
    private String facultyName;

    public StudentCard(String name){
        this.name = name;
    }
}
