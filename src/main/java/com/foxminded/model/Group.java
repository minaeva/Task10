package com.foxminded.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class Group {

    private long id;
    private String name;
    @EqualsAndHashCode.Exclude
    private List<StudentCard> students = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }
}
