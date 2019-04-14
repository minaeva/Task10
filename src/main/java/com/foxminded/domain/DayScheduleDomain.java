package com.foxminded.domain;

import com.foxminded.dao.DayScheduleDao;
import com.foxminded.dao.impl.DayScheduleDaoImpl;
import com.foxminded.model.DaySchedule;
import com.foxminded.model.Faculty;
import com.foxminded.model.Lesson;
import java.util.List;

public class DayScheduleDomain {

    private static DayScheduleDao dayScheduleDao = new DayScheduleDaoImpl();

    public DaySchedule createDaySchedule(DaySchedule daySchedule){
        return dayScheduleDao.create(daySchedule);
    }

    public DaySchedule findDaySchedule(long id){
        return dayScheduleDao.findById(id);
    }

    public void removeDaySchedule(DaySchedule daySchedule){
        dayScheduleDao.delete(daySchedule);
    }

    public static List<DaySchedule> findAllDaySchedules(Faculty faculty) {
        return dayScheduleDao.findAllByFacultyId(faculty.getId());
    }

    public static Lesson addLesson(DaySchedule daySchedule, Lesson lesson) {
        Lesson newLesson = LessonDomain.createLesson(lesson);

        daySchedule.getLessons().add(newLesson);

        dayScheduleDao.addLesson(lesson, daySchedule.getId());

        return lesson;
    }

    public static void removeLesson(DaySchedule daySchedule, Lesson lesson) {
        daySchedule.getLessons().remove(lesson);

        LessonDomain.removeLesson(lesson);
    }

}
