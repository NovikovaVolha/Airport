package com.academy.model.repository;

import java.util.List;

public interface DefaultRepository<T, I> {

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(I id);

}
