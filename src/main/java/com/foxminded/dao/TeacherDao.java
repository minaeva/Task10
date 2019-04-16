package com.foxminded.dao;

import com.foxminded.model.TeacherCard;

import java.util.List;

public interface TeacherDao extends CrudDao<TeacherCard> {

    List<TeacherCard> findTeachersByFacultyId(long facultyId);

    TeacherCard addSubjectId(TeacherCard teacher, long subjectId);
}
