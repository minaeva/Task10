package com.foxminded.domain;

import com.foxminded.dao.LessonDao;
import com.foxminded.dao.impl.LessonDaoImpl;
import com.foxminded.model.Auditorium;
import com.foxminded.model.Lesson;
import com.foxminded.model.Subject;
import com.foxminded.model.TeacherCard;

import java.util.List;

public class LessonDomain {

    private LessonDao lessonDao = new LessonDaoImpl();

    public Lesson createLesson(Lesson lesson) {
        return lessonDao.create(lesson);
    }

    public Lesson addTeacher (Lesson lesson, TeacherCard teacher) {
        return lessonDao.addTeacher(lesson, teacher);
    }

    public Lesson addAuditorium (Lesson lesson, Auditorium auditorium) {
        return lessonDao.addAuditorium(lesson, auditorium);
    }

    public Lesson addSubject (Lesson lesson, Subject subject) {
        return lessonDao.addSubject(lesson, subject);
    }

    public Lesson findLessonById(long id) {
        return lessonDao.findById(id);
    }

    public List<Lesson> findLessonsByGroupId(long groupId) {
        return lessonDao.findLessonsByGroupId(groupId);
    }

    public List<Lesson> findLessonsByTeacherId(long teacherId) {
        return lessonDao.findLessonsByTeacherId(teacherId);
    }

    public List<Lesson> findAllLessons() {
        return lessonDao.findAll();
    }

    public Lesson updateLesson(Lesson lesson) {
        return lessonDao.update(lesson);
    }

    public void removeLesson(Lesson lesson) {
        lessonDao.delete(lesson);
    }
}
