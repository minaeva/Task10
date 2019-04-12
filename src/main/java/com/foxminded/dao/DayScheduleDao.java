package com.foxminded.dao;

import com.foxminded.model.DaySchedule;

import java.util.List;

public interface DayScheduleDao extends CrudDao<DaySchedule> {

    List<DaySchedule> findAllByFacultyId(final long facultyId);

    DaySchedule addFacultyId(DaySchedule daySchedule, long facultyId);

}
