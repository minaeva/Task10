package com.foxminded.domain;

import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.Group;
import com.foxminded.model.Lesson;
import com.foxminded.model.StudentCard;
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

    public List<Lesson> findSchedule(StudentCard student) {
        GroupDomain groupDomain = new GroupDomain();
        Group group = groupDomain.findGroupByStudent(student);

        LessonDomain lessonDomain = new LessonDomain();
        return lessonDomain.findLessonsByGroupId(group.getId());
    }

}
