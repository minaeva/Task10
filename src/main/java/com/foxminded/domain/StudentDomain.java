package com.foxminded.domain;

import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.StudentCard;

import java.util.List;

public class StudentDomain {

    private static StudentDao studentDao = new StudentDaoImpl();

    public static StudentCard createStudent(StudentCard student) {
        return studentDao.create(student);
    }

    public static StudentCard updateStudent(StudentCard student) {
        return studentDao.update(student);
    }

    public static StudentCard findStudent(long id) {
        return studentDao.findById(id);
    }

    public List<StudentCard> findStudentsByGroup(long groupId) {
        return studentDao.findAllGroupStudents(groupId);
    }

    public void dismissStudent(StudentCard student){
        studentDao.delete(student);
    }
}
