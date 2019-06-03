package com.foxminded.domain;

import com.foxminded.dao.AuditoriumDao;
import com.foxminded.dao.impl.AuditoriumDaoImpl;
import com.foxminded.model.Auditorium;
import java.util.List;

public class  AuditoriumDomain {

    private AuditoriumDao auditoriumDao = new AuditoriumDaoImpl();

    public Auditorium createAuditorium(Auditorium auditorium) {
        return auditoriumDao.create(auditorium);
    }

    public Auditorium findAuditoriumById(long id) {
        return auditoriumDao.findById(id);
    }

    public List<Auditorium> findAuditoriaByFacultyId(long facultyId) {
        return auditoriumDao.findAuditoriaByFacultyId(facultyId);
    }

    public List<Auditorium> findAll() {
        return auditoriumDao.findAll();
    }

    public Auditorium updateAuditorium (Auditorium auditorium) {
        return auditoriumDao.update(auditorium);
    }

    public void removeAuditorium(Auditorium auditorium) {
        auditoriumDao.delete(auditorium);
    }
}
