package com.foxminded.dao;

import com.foxminded.model.Auditorium;

public interface AuditoriumDao extends CrudDao<Auditorium> {

    Auditorium addFacultyId(Auditorium auditorium, long facultyId);
}
