package com.foxminded.dao;

import com.foxminded.model.Lesson;

import java.util.List;

public interface DayScheduleDao extends CrudDao<DaySchedule> {

    List<DaySchedule> findAllByFacultyId(final long facultyId);

    DaySchedule addLesson(Lesson lesson, long dayScheduleId);
}
