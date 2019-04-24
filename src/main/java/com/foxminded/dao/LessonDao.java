package com.foxminded.dao;

import com.foxminded.model.Auditorium;
import com.foxminded.model.Lesson;
import com.foxminded.model.Subject;
import com.foxminded.model.TeacherCard;

import java.util.List;

public interface LessonDao extends CrudDao<Lesson> {

    Lesson addTeacher(Lesson lesson, TeacherCard teacher);

    Lesson addSubject(Lesson lesson, Subject subject);

    Lesson addAuditorium(Lesson lesson, Auditorium auditorium);

    List<Lesson> findLessonsByGroupId(long groupId);

    List<Lesson> findLessonsByTeacherId(long teacherId);

    long findGroupIdByLessonId(final long lessonId);

    long findSubjectIdByLessonId(final long subjectId);

    long findTeacherIdByLessonId(final long teacherId);

    long findAuditoriumIdByLessonId(final long lessonId);
}
