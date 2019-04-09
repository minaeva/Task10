package com.foxminded.model;

import lombok.Data;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private long id;
    private List<DaySchedule> daySchedules = new ArrayList<>();

    public DaySchedule createDaySchedule(DayOfWeek workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public DaySchedule findDaySchedule(DayOfWeek day) {
        for (DaySchedule daySchedule: daySchedules) {
            if (daySchedule.getWorkDay().equals(day)) {
                return daySchedule;
            }
        }
        return null;
    }

    public boolean removeDaySchedule(DayOfWeek day) {
        for (DaySchedule daySchedule: daySchedules) {
            if (daySchedule.getWorkDay().equals(day)) {
                daySchedules.remove(daySchedule);
                return true;
            }
        }
        return false;
    }
}
