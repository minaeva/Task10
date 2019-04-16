package com.foxminded.domain;

import com.foxminded.dao.TeacherDao;
import com.foxminded.dao.impl.TeacherDaoImpl;
import com.foxminded.model.Lesson;
import com.foxminded.model.Subject;
import com.foxminded.model.TeacherCard;
import java.util.List;

public class TeacherDomain {

    private TeacherDao teacherDao = new TeacherDaoImpl();

    public TeacherCard createTeacher(TeacherCard teacher){
        return teacherDao.create(teacher);
    }

    public TeacherCard findTeacherById(long id) {
        return teacherDao.findById(id);
    }

    public List<TeacherCard> findAllTeachers() {
        return teacherDao.findAll();
    }

    public List<TeacherCard> findTeachersByFacultyId(long facultyId) {
        return teacherDao.findTeachersByFacultyId(facultyId);
    }

    public TeacherCard updateTeacher(TeacherCard teacher){
        return teacherDao.update(teacher);
    }

    public void dismissTeacher(TeacherCard teacher){
        teacherDao.delete(teacher);
    }

    public TeacherCard addSubject(TeacherCard teacher, Subject subject){
        return teacherDao.addSubjectId(teacher, subject.getId());
    }

    public List<Lesson> createSchedule(TeacherCard teacher) {
        LessonDomain lessonDomain = new LessonDomain();
        return lessonDomain.findLessonsByTeacherId(teacher.getId());
    }

}
