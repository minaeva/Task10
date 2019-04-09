package com.foxminded.model;

import lombok.Data;
import java.util.*;

@Data
public class Group {

    private long id;
    private String name;
    private List<StudentCard> students = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }
}
