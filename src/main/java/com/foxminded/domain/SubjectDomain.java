package com.foxminded.domain;

import com.foxminded.dao.SubjectDao;
import com.foxminded.dao.impl.SubjectDaoImpl;
import com.foxminded.model.Subject;

public class SubjectDomain {

    private static SubjectDao subjectDao = new SubjectDaoImpl();

    public static Subject createSubject(Subject subject) {
        return subjectDao.create(subject);
    }

    public static Subject findSubject(long id){
        return subjectDao.findById(id);
    }

    public static Subject updateSubject(Subject subject){
        return subjectDao.update(subject);
    }

    public static void removeSubject(Subject subject) {
        subjectDao.delete(subject);
    }
}
