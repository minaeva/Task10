package com.foxminded.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalTime;

@Data
public class Pair {

    private LocalTime startTime;
    private Lesson lesson;
    private final int DURATION = 90;

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }

    public Lesson createLesson(String groupName, String subjectName,
                               String mentorName, int auditoriumNumber){
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

        this.lesson = new Lesson();
        lesson.setGroupName(groupName);
        lesson.setSubjectName(subjectName);
        lesson.setMentorName(mentorName);
        lesson.setAuditoriumNumber(auditoriumNumber);
        return lesson;
    }

    public void removeLesson() {
        this.lesson = null;
    }
}
