package com.foxminded.domain;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.StudentDao;
import com.foxminded.dao.impl.GroupDaoImpl;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.model.StudentCard;
import com.foxminded.model.Group;

import java.util.List;

public class GroupDomain {

    private GroupDao groupDao = new GroupDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    public Group createGroup(Group group) {
        return groupDao.create(group);
    }

    public Group findGroupById(long id) {
        return groupDao.findById(id);
    }

    public Group findGroupByIdFull(long id) throws IllegalArgumentException{
        Group group = groupDao.findById(id);
        if (group == null) {
            throw new IllegalArgumentException();
        }
        group.setStudents(studentDao.findStudentsByGroupId(group.getId()));
        return group;
    }

    public Group findGroupByStudent(StudentCard student) {
        return groupDao.findGroupByStudentId(student.getId());
    }

    public Group findGroupByStudentFull(StudentCard student) {
        Group group = groupDao.findGroupByStudentId(student.getId());
        group.setStudents(studentDao.findStudentsByGroupId(group.getId()));
        return group;
    }

    public List<Group> findGroupsByFaculty(long facultyId) {
        return groupDao.findGroupsByFacultyId(facultyId);
    }

    public List<Group> findAll() {
        return groupDao.findAll();
    }

    public Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    public void removeGroup(Group group) {
        groupDao.delete(group);
    }

    public Group addStudent(Group group, StudentCard student) {
        group.getStudents().add(student);
        return groupDao.addStudent(group.getId(), student);
    }

    public void removeStudent(Group group, StudentCard student) {
        groupDao.removeStudent(group.getId(), student);
        group.getStudents().remove(student);
    }

    public Group moveStudentToGroup(StudentCard student, Group newGroup) {
        Group oldGroup = findGroupByStudentFull(student);
        if (oldGroup != null) {
            removeStudent(oldGroup, student);
        }
        addStudent(newGroup, student);
        return newGroup;
    }
}
