package com.foxminded.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Lesson {

    private long id;
    private Group group;
    private TeacherCard teacher;
    private Subject subject;
    private Auditorium auditorium;
    private LocalDateTime startDateTime;

    public Lesson(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
