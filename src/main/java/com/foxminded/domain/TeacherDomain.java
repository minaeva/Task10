package com.foxminded.domain;

import com.foxminded.dao.TeacherDao;
import com.foxminded.dao.impl.TeacherDaoImpl;
import com.foxminded.model.Subject;
import com.foxminded.model.TeacherCard;

public class TeacherDomain {

    private static TeacherDao teacherDao = new TeacherDaoImpl();

    public static TeacherCard createTeacher(TeacherCard teacher){
        return teacherDao.create(teacher);
    }

    public static TeacherCard addSubject(TeacherCard teacher, Subject subject){
        return teacherDao.addSubjectId(teacher, subject.getId());
    }

    public static TeacherCard findTeacher(long id) {
        return teacherDao.findById(id);
    }

    public static TeacherCard updateTeacher(TeacherCard teacher){
        return teacherDao.update(teacher);
    }

    public static void dismissTeacher(TeacherCard teacher){
        teacherDao.delete(teacher);
    }

}
