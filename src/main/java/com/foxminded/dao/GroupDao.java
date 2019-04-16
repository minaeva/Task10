package com.foxminded.dao;

import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

import java.util.List;

public interface GroupDao extends CrudDao<Group> {

    Group addStudent(long groupId, StudentCard student);

    void removeStudent(long groupId, StudentCard student);

    Group findGroupByStudentId(final long studentId);

    List<Group> findGroupsByFacultyId(long facultyId);
}
