package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.aspect.Loggable;
import com.dmitrybondarev.shop.repository.api.GenericRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class GenericRepoImpl<T extends Serializable> implements GenericRepo<T> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    @Loggable
    void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    Class<T> getClazz() {
        return clazz;
    }

    @Loggable
    public T findById(final Long id){
        return entityManager.find(clazz, id);
    }

    @Loggable
    public List<T> findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    @Loggable
    public void save(final T entity){
        entityManager.persist(entity);
    }

    @Loggable
    public void update(final T entity){
        entityManager.merge(entity);
    }

    @Loggable
    public void delete(final T entity){
        entityManager.remove(entity);
    }

    @Loggable
    public void deleteById(final Long entityId ){
        T entity = findById(entityId);
        delete(entity);
    }
}
