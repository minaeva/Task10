package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentCard {

    private long id;
    private String name;

    public StudentCard(String name){
        this.name = name;
    }
}
