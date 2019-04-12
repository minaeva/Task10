package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

public class StudentCardDomain {

    private StudentDao studentDao = new StudentDaoImpl();

    StudentCardDomain(StudentCard student) {
        studentDao.create(student);
    }

    public StudentCard addGroupId(StudentCard student, Group group) {
        return studentDao.addGroupId(student, group.getId());
    }

    public StudentCard findStudent(StudentCard student) {
        return studentDao.findById(student.getId());
    }

    public boolean dismissStudent(StudentCard student) {
        try {
            studentDao.delete(student);
        } catch (
        DaoException e) {
            return false;
        }
            return true;
    }
}
