package com.foxminded.domain;

import com.foxminded.dao.LessonDao;
import com.foxminded.dao.impl.LessonDaoImpl;
import com.foxminded.model.Auditorium;
import com.foxminded.model.Lesson;
import com.foxminded.model.TeacherCard;

public class LessonDomain {

    private static LessonDao lessonDao = new LessonDaoImpl();

    public static Lesson createLesson(Lesson lesson) {
        return lessonDao.create(lesson);
    }

    public static Lesson addTeacher (Lesson lesson, TeacherCard teacher) {
        return lessonDao.addTeacher(lesson, teacher);
    }

    public static Lesson addAuditorium (Lesson lesson, Auditorium auditorium) {
        return lessonDao.addAuditorium(lesson, auditorium);
    }

    public static Lesson findLesson(long id){
        return lessonDao.findById(id);
    }

    public static Lesson updateLesson(Lesson lesson) {
        return lessonDao.update(lesson);
    }

    public static void removeLesson(Lesson lesson) {
        lessonDao.delete(lesson);
    }
}
