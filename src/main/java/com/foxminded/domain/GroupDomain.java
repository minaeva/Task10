package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.GroupDao;
import com.foxminded.dao.impl.GroupDaoImpl;
import com.foxminded.model.StudentCard;
import com.foxminded.model.Group;
import java.util.List;

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
        StudentCard newStudent = StudentDomain.createStudent(student);

        group.getStudents().add(newStudent);

        return groupDao.addStudent(group.getId(), newStudent);
    }

    public static StudentCard changeStudentGroup(Group newGroup, StudentCard student) {
        long oldGroupId = findGroupByStudent(student);
        Group group = findGroupById(oldGroupId);

        group.getStudents().remove(student);
        newGroup.getStudents().add(student);

        groupDao.removeStudent(oldGroupId, student);
        return groupDao.addStudent(newGroup.getId(), student);
    }

    public List<StudentCard> findStudentsByGroup(long groupId) {
        return groupDao.findAllGroupStudents(groupId);
    }

    public static boolean removeStudent(Group group, StudentCard student) {
        group.getStudents().remove(student);

        try {
            groupDao.removeStudent(group.getId(), student);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }
}
