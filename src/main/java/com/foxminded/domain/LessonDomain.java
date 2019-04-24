package com.foxminded.domain;

import com.foxminded.dao.*;
import com.foxminded.dao.impl.*;
import com.foxminded.model.Auditorium;
import com.foxminded.model.Lesson;
import com.foxminded.model.Subject;
import com.foxminded.model.TeacherCard;
import java.util.List;

public class LessonDomain {

    private LessonDao lessonDao = new LessonDaoImpl();
    private AuditoriumDao auditoriumDao = new AuditoriumDaoImpl();
    private GroupDao groupDao = new GroupDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();
    private TeacherDao teacherDao = new TeacherDaoImpl();

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
        List<Lesson> lessons = lessonDao.findLessonsByGroupId(groupId);
        for (Lesson lesson: lessons) {
            lesson.setGroup(groupDao.findById(lessonDao.findGroupIdByLessonId(lesson.getId())));
            lesson.setTeacher(teacherDao.findById(lessonDao.findTeacherIdByLessonId(lesson.getId())));
            lesson.setSubject(subjectDao.findById(lessonDao.findSubjectIdByLessonId(lesson.getId())));
            lesson.setAuditorium(auditoriumDao.findById(lessonDao.findAuditoriumIdByLessonId(lesson.getId())));
        }
        return lessons;
    }

    public List<Lesson> findLessonsByTeacherId(long teacherId) {
        List<Lesson> lessons = lessonDao.findLessonsByTeacherId(teacherId);
        for (Lesson lesson: lessons) {
            lesson.setGroup(groupDao.findById(lessonDao.findGroupIdByLessonId(lesson.getId())));
            lesson.setTeacher(teacherDao.findById(lessonDao.findTeacherIdByLessonId(lesson.getId())));
            lesson.setSubject(subjectDao.findById(lessonDao.findSubjectIdByLessonId(lesson.getId())));
            lesson.setAuditorium(auditoriumDao.findById(lessonDao.findAuditoriumIdByLessonId(lesson.getId())));
        }
        return lessons;
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
