package com.foxminded.dao;

import com.foxminded.model.Lesson;

public interface LessonDao extends CrudDao<Lesson> {

    Lesson addDayScheduleId(Lesson lesson, long dayScheduleId);
}
