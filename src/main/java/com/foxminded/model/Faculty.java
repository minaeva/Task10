package com.foxminded.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Faculty {

    private long id;
    private String name;
    @EqualsAndHashCode.Exclude
    private List<Group> groups = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    private List<TeacherCard> teachers = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    private List<Subject> subjects = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    private List<Auditorium> auditoria = new ArrayList<>();
    @EqualsAndHashCode.Exclude
    private List<Lesson> schedule = new ArrayList<>();

    public Faculty(String name) {
        this.name = name;
    }
}
