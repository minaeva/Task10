package com.foxminded.dao;

import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

public interface GroupDao extends CrudDao<Group> {

    Group addStudent(long groupId, StudentCard student);

    void removeStudent(long groupId, StudentCard student);

    long findByStudentId(final long studentId);
}
