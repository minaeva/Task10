package com.foxminded.dao;

import com.foxminded.model.StudentCard;

public interface StudentDao extends CrudDao<StudentCard> {

    StudentCard addGroupId(StudentCard student, long groupId);
}
