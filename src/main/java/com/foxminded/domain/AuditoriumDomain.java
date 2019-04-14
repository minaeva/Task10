package com.foxminded.domain;

import com.foxminded.dao.AuditoriumDao;
import com.foxminded.dao.impl.AuditoriumDaoImpl;
import com.foxminded.model.Auditorium;

public class AuditoriumDomain {

    private static AuditoriumDao auditoriumDao = new AuditoriumDaoImpl();

    public static Auditorium createAuditorium(Auditorium auditorium) {
        return auditoriumDao.create(auditorium);
    }

    public static Auditorium findAuditorium(long id) {
        return auditoriumDao.findById(id);
    }

    public static Auditorium updateAuditorium (Auditorium auditorium) {
        return auditoriumDao.update(auditorium);
    }

    public static void removeAuditorium(Auditorium auditorium) {
        auditoriumDao.delete(auditorium);
    }
}
