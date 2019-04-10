package com.foxminded.model;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Lesson {

    private long id;
    private Group group;
    private TeacherCard teacher;
    private Auditorium auditorium;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(LocalTime startTime) {
        this.startTime = startTime;
    }
}
