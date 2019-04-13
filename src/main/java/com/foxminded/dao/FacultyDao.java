package com.foxminded.dao;

import com.foxminded.model.*;
import java.util.List;

public interface FacultyDao extends CrudDao<Faculty> {

    Group addGroup(long facultyId, Group group);

    void removeGroup(long facultyId, Group group);

    List<Group> findAllFacultyGroups(long facultyId);

    long findByGroupId(final long groupId);

    TeacherCard addTeacher(long facultyId, TeacherCard teacher);

    Subject addSubject(long facultyId, Subject subject);

    Auditorium addAuditorium(long facultyId, Auditorium auditorium);

}
