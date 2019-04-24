package com.foxminded.dao;

import com.foxminded.model.Subject;
import java.util.List;

public interface SubjectDao extends CrudDao<Subject> {

    List<Subject> findSubjectsByFacultyId(long facultyId);
}
