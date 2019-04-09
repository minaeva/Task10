package com.foxminded.model;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DaySchedule {

    private long id;
    private DayOfWeek workDay;
    private List<Pair> pairs = new ArrayList<>();

    public DaySchedule(DayOfWeek workDay){
        this.workDay = workDay;
    }
}
