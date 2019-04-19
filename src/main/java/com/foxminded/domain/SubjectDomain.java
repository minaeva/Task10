package com.foxminded.domain;

import com.foxminded.dao.SubjectDao;
import com.foxminded.dao.impl.SubjectDaoImpl;
import com.foxminded.model.Subject;

import java.util.List;

public class SubjectDomain {

    private SubjectDao subjectDao = new SubjectDaoImpl();

    public Subject createSubject(Subject subject) {
        return subjectDao.create(subject);
    }

    public Subject findSubjectById(long id) {
        return subjectDao.findById(id);
    }

    public List<Subject> findAll() {
        return subjectDao.findAll();
    }

    public Subject updateSubject(Subject subject){
        return subjectDao.update(subject);
    }

    public void removeSubject(Subject subject) {
        subjectDao.delete(subject);
    }
}
