package com.foxminded.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import static com.foxminded.domain.Validator.*;

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
        if (StringUtils.isBlank(groupName)) {
            throw new IllegalArgumentException("Group name cannot be empty");
        }
        if (StringUtils.isBlank(subjectName)) {
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        if (StringUtils.isBlank(mentorName)) {
            throw new IllegalArgumentException("Mentor name cannot be empty");
        }
        if (auditoriumNumber < 0) {
            throw new IllegalArgumentException("Auditorium number cannot be negative");
        }

        Pair pair = new Pair(startTime);
        pair.setGroupName(groupName);
        pair.setSubjectName(subjectName);
        pair.setMentorName(mentorName);
        pair.setAuditoriumNumber(auditoriumNumber);
        pairs.add(pair);
        return pair;
    }

    public Pair findPair(LocalTime startTime){
        return findObjectByTimeIfExists(pairs,
                pair -> Objects.equals(pair.getStartTime(), startTime),
                "Lesson",
                startTime);
    }

    public boolean removePair(LocalTime startTime) {
        return pairs.removeIf(pair -> Objects.equals(pair.getStartTime(), startTime));
    }
}
