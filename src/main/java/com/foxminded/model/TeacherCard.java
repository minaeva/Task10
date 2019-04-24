package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherCard {

    private long id;
    private String name;
    private Subject subject;

    public TeacherCard(String name){
        this.name = name;
    }
}
