package com.foxminded.dao;

import com.foxminded.model.TeacherCard;

public interface TeacherDao extends CrudDao<TeacherCard> {

    TeacherCard addFacultyId(TeacherCard teacher, long facultyId);

    TeacherCard addSubjectId(TeacherCard teacher, long subjectId);
}
