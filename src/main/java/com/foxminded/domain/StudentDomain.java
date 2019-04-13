package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.StudentCard;

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

    public boolean dismissStudent(StudentCard student){
        try {
            studentDao.delete(student);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }
}
