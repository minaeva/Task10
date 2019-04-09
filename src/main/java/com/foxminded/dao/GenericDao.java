package com.foxminded.dao;

import java.util.List;

public interface GenericDao<T> {
/*?
public interface CrudDao<T> {
?*/
    T create(T t);

    T findById(final long id);

    List<T> findAll();

    T update(final T t);

    void delete(T t);
}
