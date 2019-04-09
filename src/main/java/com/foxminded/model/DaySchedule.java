package com.foxminded.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
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

    public DaySchedule getDaySchedule(){
        return this;
    }

    public Pair createPair(LocalTime startTime, String groupName, String subjectName,
                           String mentorName, int auditoriumNumber) {
        Pair pair = new Pair(startTime);
        pair.setGroupName(groupName);
        pair.setSubjectName(subjectName);
        pair.setMentorName(mentorName);
        pair.setAuditoriumNumber(auditoriumNumber);
        pairs.add(pair);
        return pair;
    }

    public Pair findPair(LocalTime startTime){
        for (Pair pair: pairs) {
            if (pair.getStartTime().equals(startTime)) {
                return pair;
            }
        }
        return null;
    }

    public boolean removePair(LocalTime startTime) {
        for (Pair pair: pairs) {
            if (pair.getStartTime().equals(startTime)) {
                pairs.remove(pair);
                return true;
            }
        }
        return false;
    }
}
