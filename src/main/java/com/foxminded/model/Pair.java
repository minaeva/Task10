package com.foxminded.model;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Pair {

    private long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String groupName;
    private String subjectName;
    private String mentorName;
    private int auditoriumNumber;

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }
}
