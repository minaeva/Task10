package com.foxminded.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import static com.foxminded.domain.Validator.*;

@Data
public class DaySchedule {

    private WorkDay workDay;
    private List<Pair> pairs = new ArrayList<>();

    public DaySchedule(WorkDay workDay){
        this.workDay = workDay;
    }

    public DaySchedule getDaySchedule(){
    return this;
    }

    public Pair createPair(LocalTime startTime) {
        Pair pair = new Pair(startTime);
        pairs.add(pair);
        return pair;
    }

    public Pair findPair(LocalTime startTime){
        return findObjectByTimeIfExists(pairs,
                pair -> Objects.equals(pair.getStartTime(), startTime),
                "Pair",
                startTime);
    }

    public boolean removePair(LocalTime startTime) {
        return pairs.removeIf(pair -> Objects.equals(pair.getStartTime(), startTime));
    }
}