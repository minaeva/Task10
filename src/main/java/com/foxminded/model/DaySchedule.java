package com.foxminded.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DaySchedule {

    private long id;
    private DayOfWeek workDay;
    private List<Lesson> lessons = new ArrayList<>();

    public DaySchedule(DayOfWeek workDay){
        this.workDay = workDay;
    }
}
