package com.foxminded.domain;

import com.foxminded.dao.LessonDao;
import com.foxminded.dao.DaoException;
import com.foxminded.dao.impl.LessonDaoImpl;
import com.foxminded.model.Lesson;

public class DayScheduleDomain {

    private LessonDao lessonDao = new LessonDaoImpl();

    public Lesson createLesson(Lesson lesson, long dayScheduleId) {
        Lesson newLesson = lessonDao.create(lesson);
        return lessonDao.addDayScheduleId(newLesson, dayScheduleId);
    }

    public Lesson findLesson(Lesson lesson){
        return lessonDao.findById(lesson.getId());
    }

    public boolean removeLesson(Lesson lesson) {
        try {
            lessonDao.delete(lesson);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }
}
