package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.util.aspect.Loggable;
import com.dmitrybondarev.shop.repository.api.GenericRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class GenericRepoImpl<T extends Serializable> implements GenericRepo<T> {

    private Class<T> clazz;

    @PersistenceContext
    protected EntityManager entityManager;

    @Loggable
    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    @Override
    @Loggable
    public T findById(final Long id){
        return entityManager.find(clazz, id);
    }

    @Override
    @Loggable
    public List<T> findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    @Override
    @Loggable
    public void save(final T entity){
        entityManager.persist(entity);
    }

    @Override
    @Loggable
    public void update(final T entity){
        entityManager.merge(entity);
    }

    @Override
    @Loggable
    public void delete(final T entity){
        entityManager.remove(entity);
    }

    @Override
    @Loggable
    public void deleteById(final Long entityId ){
        T entity = findById(entityId);
        delete(entity);
    }
}
