package com.dmitrybondarev.shop.repository.api;

import java.io.Serializable;
import java.util.List;

public interface GenericRepo<T extends Serializable> {

    T findById(final Long id);

    List<T> findAll();

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void deleteById(final Long entityId);
}

