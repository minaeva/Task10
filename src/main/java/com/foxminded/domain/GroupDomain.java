package com.foxminded.domain;

import com.foxminded.dao.DaoException;
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
        Group createdGroup = null;
        try {
            createdGroup = groupDao.create(group);
        } catch (DaoException e) {
            throw new DomainException("Cannot create group " + group, e);
        }
        return createdGroup;
    }

    public Group findGroupById(long id) {
        Group group = null;
        try {
            group = groupDao.findById(id);
        } catch (DaoException e) {
            throw new DomainException("Cannot create group " + group, e);
        }

        if (group == null) {
            throw new DomainNotFoundException();
        }
        return group;
    }

    public Group findGroupByIdFull(long id) {
        Group group = groupDao.findById(id);
        group.setStudents(studentDao.findStudentsByGroupId(group.getId()));
        return group;
    }

    public Group findGroupByStudent(StudentCard student) {
        Group group = groupDao.findGroupByStudentId(student.getId());
        if (group == null) {
            throw new DomainNotFoundException();
        }
        return group;
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
        findGroupById(group.getId());
        return groupDao.update(group);
    }

    public void removeGroup(Group group) {
        findGroupById(group.getId());
        groupDao.delete(group);
    }

    public Group addStudent(StudentCard student, Group group) {
        group.getStudents().add(student);
        return groupDao.addStudent(group.getId(), student);
    }

    public void removeStudent(Group group, StudentCard student) {
        groupDao.removeStudent(group.getId(), student);
        group.getStudents().remove(student);
    }
}
