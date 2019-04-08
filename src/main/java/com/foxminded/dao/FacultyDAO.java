package com.foxminded.dao;

import com.foxminded.domain.Faculty;

import java.util.List;

public interface FacultyDAO {
    Faculty insert(Faculty faculty);

    Faculty findById(final long id);

    List<Faculty> findAll();

    Faculty update(final Faculty faculty);

    void delete(Faculty faculty);
}
