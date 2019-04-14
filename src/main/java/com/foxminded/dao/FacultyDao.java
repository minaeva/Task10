package com.foxminded.dao;

import com.foxminded.model.*;
import java.util.List;

public interface FacultyDao extends CrudDao<Faculty> {

    Faculty addGroup(long facultyId, Group group);

    void removeGroup(long facultyId, Group group);

    List<Group> findAllFacultyGroups(long facultyId);

    long findByGroupId(final long groupId);

    Faculty addTeacher(long facultyId, TeacherCard teacher);

    Faculty addAuditorium(long facultyId, Auditorium auditorium);

    Faculty addSubject(long facultyId, Subject subject);

    Faculty addDaySchedule(long facultyId, DaySchedule daySchedule);
}
