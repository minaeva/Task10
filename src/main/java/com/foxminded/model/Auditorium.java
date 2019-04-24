package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Auditorium {

    private long id;
    private int number;

    public Auditorium(int number){
        this.number = number;
    }
}
