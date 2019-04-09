package com.foxminded.domain;

import lombok.Data;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.foxminded.domain.Validator.*;

@Data
public class Schedule {

    private long id;
    private List<DaySchedule> daySchedules = new ArrayList<>();

    public DaySchedule createDaySchedule(DayOfWeek workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public boolean removeDaySchedule(DayOfWeek day){
        return daySchedules.removeIf(daySchedule -> Objects.equals(daySchedule.getWorkDay(), day));
    }

    public DaySchedule findDaySchedule(DayOfWeek day){
        return findObjectByWorkdayIfExists(daySchedules,
                daySchedule -> Objects.equals(daySchedule.getWorkDay(), day),
                "Day schedule",
                day);
    }
}
