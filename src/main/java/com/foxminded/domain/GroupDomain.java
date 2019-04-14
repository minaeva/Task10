package com.foxminded.domain;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.impl.GroupDaoImpl;
import com.foxminded.model.StudentCard;
import com.foxminded.model.Group;

public class GroupDomain {

    private static GroupDao groupDao = new GroupDaoImpl();

    public static Group createGroup(Group group) {
        return groupDao.create(group);
    }

    public static Group findGroupById(long id) {
        return groupDao.findById(id);
    }

    public static long findGroupByStudent(StudentCard student) {
        return groupDao.findByStudentId(student.getId());
    }

    public static Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    public static void removeGroup(Group group) {
        groupDao.delete(group);
    }

    public static StudentCard addStudent(Group group, StudentCard student){
        group.getStudents().add(student);

        groupDao.addStudent(group.getId(), student);

        return student;
    }

    public static StudentCard changeStudentGroup(Group newGroup, StudentCard student) {
        long oldGroupId = findGroupByStudent(student);
        Group group = findGroupById(oldGroupId);

        group.getStudents().remove(student);
        newGroup.getStudents().add(student);

        groupDao.removeStudent(oldGroupId, student);
        groupDao.addStudent(newGroup.getId(), student);

        return student;
    }

    public static void removeStudent(Group group, StudentCard student) {
        group.getStudents().remove(student);

        groupDao.removeStudent(group.getId(), student);
    }
}
