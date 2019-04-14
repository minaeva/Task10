package com.foxminded.dao;

import com.foxminded.model.Auditorium;
import com.foxminded.model.Lesson;
import com.foxminded.model.TeacherCard;

public interface LessonDao extends CrudDao<Lesson> {

    Lesson addTeacher(Lesson lesson, TeacherCard teacher);

    Lesson addAuditorium(Lesson lesson, Auditorium auditorium);
}
