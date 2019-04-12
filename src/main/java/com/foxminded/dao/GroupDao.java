package com.foxminded.dao;

import com.foxminded.model.Group;

public interface GroupDao extends CrudDao<Group> {

    Group addFacultyId(Group group, long facultyId);
}
