package com.foxminded.dao;

import com.foxminded.model.Auditorium;
import java.util.List;

public interface AuditoriumDao extends CrudDao<Auditorium> {

    Auditorium addFacultyId(Auditorium auditorium, long facultyId);

    List<Auditorium> findAuditoriaByFacultyId(long facultyId);

    Auditorium findAuditoriumByLessonId(final long auditoriumId);
}
