package com.foxminded.model;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Pair {

    private long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String groupName;
    private Subject subject;
    private TeacherCard teacher;
    private Auditorium auditorium;

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }
}
