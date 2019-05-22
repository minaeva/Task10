package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.Group;
import com.foxminded.model.Lesson;
import com.foxminded.model.StudentCard;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StudentDomain {

    private StudentDao studentDao = new StudentDaoImpl();

    public StudentCard createStudent(StudentCard student) {
        StudentCard createdStudent = null;
        try {
            createdStudent = studentDao.create(student);
        } catch (DaoException e) {
            throw new DomainException("Cannot create student " + student, e);
        }
        return createdStudent;
    }

    public StudentCard findStudentById(long id) {
        StudentCard student = null;
        try {
            student = studentDao.findById(id);
        } catch (DaoException e) {
            throw new DomainException("Cannot find student with id " + id, e);
        }

        if (student == null) {
            throw new DomainNotFoundException();
        }
        return student;
    }

    public List<StudentCard> findAll() {
        List<StudentCard> students = null;
        try {
            students = studentDao.findAll();
        } catch (DaoException e) {
            throw new DomainException("Cannot find all students ", e);
        }
        return students;
    }

    public List<StudentCard> findStudentsByGroup(long groupId) {
        List<StudentCard> students = null;
        try {
            students = studentDao.findStudentsByGroupId(groupId);
        } catch (DaoException e) {
            throw new DomainException("Cannot find all students of group with id " + groupId, e);
        }
        return students;
    }

    public StudentCard updateStudent(StudentCard student) {
        findStudentById(student.getId());
        StudentCard updatedStudent = null;
        try {
            updatedStudent = studentDao.update(student);
        } catch (DaoException e) {
            throw new DomainException("Cannot update student " + student, e);
        }
        return updatedStudent;
    }

    public void dismissStudent(StudentCard student){
        findStudentById(student.getId());
        try {
            studentDao.delete(student);
        } catch (DaoException e) {
            throw new DomainException("Cannot delete student " + student, e);
        }
    }

    public void dismissStudentById(long id) {
        StudentCard student = findStudentById(id);
        try {
            studentDao.delete(student);
        } catch (DaoException e) {
            throw new DomainException("Cannot delete student with id " + id, e);
        }
    }

    public List<Lesson> findScheduleInPeriod(StudentCard student, LocalDate fromDate, LocalDate toDate) {
        GroupDomain groupDomain = new GroupDomain();
        Group group = groupDomain.findGroupByStudent(student);
        LessonDomain lessonDomain = new LessonDomain();
        List<Lesson> result = null;

        if ((fromDate == null) && (toDate == null)) {
            result = lessonDomain.findLessonsByGroupId(group.getId());
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            if (fromDate == null) {
                fromDate = LocalDate.parse("01/01/0001", formatter);
            }
            if (toDate == null) {
                toDate = LocalDate.parse("12/31/9999", formatter);
            }
            result = lessonDomain.findLessonsByGroupIdInPeriod(group.getId(), fromDate, toDate);
        }
        return result;
    }
}
