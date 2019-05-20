package com.foxminded.domain;

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
        return studentDao.create(student);
    }

    public StudentCard findStudentById(long id) {
        return studentDao.findById(id);
    }

    public List<StudentCard> findAll() {
        return studentDao.findAll();
    }

    public List<StudentCard> findStudentsByGroup(long groupId) {
        return studentDao.findStudentsByGroupId(groupId);
    }

    public StudentCard updateStudent(StudentCard student) {
        return studentDao.update(student);
    }

    public void dismissStudent(StudentCard student){
        studentDao.delete(student);
    }

    public void dismissStudentById(long id) {
        StudentCard student = findStudentById(id);
        dismissStudent(student);
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
