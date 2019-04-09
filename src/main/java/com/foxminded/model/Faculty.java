package com.foxminded.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Faculty {

    private long id;
    private String name;
    private List<Group> groups = new ArrayList<>();
    private List<TeacherCard> teachers = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Auditorium> auditoria = new ArrayList<>();
    private Schedule schedule;

    public Faculty(String name) {
        this.name = name;
    }
}
