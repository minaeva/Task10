package com.foxminded.dao;

import com.foxminded.model.Subject;

public interface SubjectDao extends CrudDao<Subject> {

    Subject addFacultyId(Subject subject, long facultyId);
}
