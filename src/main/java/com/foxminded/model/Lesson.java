package com.foxminded.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Data
public class Lesson {

    private long id;
    private Group group;
    @EqualsAndHashCode.Exclude
    private TeacherCard teacher;
    @EqualsAndHashCode.Exclude
    private Subject subject;
    @EqualsAndHashCode.Exclude
    private Auditorium auditorium;
    private LocalDateTime startDateTime;

    public Lesson(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }
}
