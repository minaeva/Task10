package com.foxminded.dao;

import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;
import java.util.List;

public interface GroupDao extends CrudDao<Group> {

    StudentCard addStudent(long groupId, StudentCard student);

    void removeStudent(long groupId, StudentCard student);

    List<StudentCard> findAllGroupStudents(long groupId);

    public long findByStudentId(final long studentId);
}
