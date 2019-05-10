package com.foxminded.domain;

import com.foxminded.dao.*;
import com.foxminded.dao.impl.*;
import com.foxminded.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
            lesson.setGroup(groupDao.findById(lesson.getGroup().getId()));
            lesson.setTeacher(teacherDao.findById(lesson.getTeacher().getId()));
            lesson.setSubject(subjectDao.findById(lesson.getSubject().getId()));
            lesson.setAuditorium(auditoriumDao.findById(lesson.getAuditorium().getId()));
        }
        return lessons;
    }

    public List<Lesson> findLessonsByTeacherId(long teacherId) {
        List<Lesson> lessons = lessonDao.findLessonsByTeacherId(teacherId);
        for (Lesson lesson: lessons) {
            lesson.setGroup(groupDao.findById(lesson.getGroup().getId()));
            lesson.setTeacher(teacherDao.findById(lesson.getTeacher().getId()));
            lesson.setSubject(subjectDao.findById(lesson.getSubject().getId()));
            lesson.setAuditorium(auditoriumDao.findById(lesson.getAuditorium().getId()));
        }
        return lessons;
    }

    public List<Lesson> findLessonsByGroupIdInPeriod(long groupId, String fromDate, String toDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate from = LocalDate.parse(fromDate, formatter);
        LocalDate to = LocalDate.parse(toDate, formatter);

        List<Lesson> allLessons = findLessonsByGroupId(groupId);
        List<Lesson> lessonsInPeriod = new ArrayList<>();
        for (Lesson lesson: allLessons) {
            LocalDate date = lesson.getStartDateTime().toLocalDate();
            if ((date.isAfter(from) || date.equals(from)) && (date.isBefore(to) || date.equals(to))) {
                lessonsInPeriod.add(lesson);
            }
        }
        return lessonsInPeriod;
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
