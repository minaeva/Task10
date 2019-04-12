package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.impl.DayScheduleDaoImpl;
import com.foxminded.model.DaySchedule;

public class Schedule {

    private DayScheduleDaoImpl dayScheduleDao = new DayScheduleDaoImpl();

    public DaySchedule createDaySchedule(DaySchedule daySchedule, long facultyId){
        DaySchedule newDaySchedule = dayScheduleDao.create(daySchedule);
        dayScheduleDao.addFacultyId(newDaySchedule, facultyId);
        return daySchedule;
    }

    public DaySchedule findDaySchedule(DaySchedule daySchedule){
        return dayScheduleDao.findById(daySchedule.getId());
    }

    public boolean removeDaySchedule(DaySchedule daySchedule){
        try {
            dayScheduleDao.delete(daySchedule);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }
}
