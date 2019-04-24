package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Subject {

    private long id;
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
