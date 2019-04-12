package com.foxminded.domain;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.impl.GroupDaoImpl;
import com.foxminded.model.StudentCard;
import com.foxminded.model.Group;

import java.util.List;

public class GroupDomain {

    private GroupDao groupDao = new GroupDaoImpl();

    public StudentCard addStudent(String studentName, Group group){
        StudentCard student = new StudentCard(studentName);

        List<StudentCard> groupStudents = group.getStudents();
        groupStudents.add(student);

        StudentCardDomain studentCardDomain = new StudentCardDomain(student);
        return studentCardDomain.addGroupId(student, group);
    }

    public StudentCard findStudent(StudentCard student){
        StudentCardDomain studentCardDomain = new StudentCardDomain(student);
        return studentCardDomain.findStudent(student);
    }

    public boolean dismissStudent(StudentCard student) {
        StudentCardDomain studentCardDomain = new StudentCardDomain(student);
        return studentCardDomain.dismissStudent(student);
    }
}
