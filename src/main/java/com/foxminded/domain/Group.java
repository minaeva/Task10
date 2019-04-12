package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.model.StudentCard;
import com.foxminded.dao.impl.StudentDaoImpl;

public class Group {

    private StudentDaoImpl studentDao = new StudentDaoImpl();

    public StudentCard takeStudent(StudentCard student, long groupId){
        StudentCard newStudent = studentDao.create(student);
        return studentDao.addGroupId(newStudent, groupId);
    }

    public StudentCard findStudent(StudentCard student){
        return studentDao.findById(student.getId());
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
