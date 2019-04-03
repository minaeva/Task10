package com.foxminded.domain;

import lombok.Data;

@Data
public class Auditorium {

    private int number;

    public Auditorium(int number){ this.number = number;}
}
