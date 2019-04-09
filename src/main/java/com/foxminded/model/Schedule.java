package com.foxminded.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private List<DaySchedule> daySchedules = new ArrayList<>();
}
