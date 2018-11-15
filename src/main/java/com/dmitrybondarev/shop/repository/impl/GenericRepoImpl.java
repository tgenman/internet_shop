package com.dmitrybondarev.shop.repository.impl;

import com.dmitrybondarev.shop.repository.api.GenericRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class GenericRepoImpl<T extends Serializable> implements GenericRepo<T> {

    private Class<T> clazz;

    @PersistenceContext
    EntityManager entityManager;

    void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    Class<T> getClazz() {
        return clazz;
    }

    public T findById(final Long id){
        return entityManager.find(clazz, id);
    }
    public List<T> findAll(){
        return entityManager.createQuery( "from " + clazz.getName() )
                .getResultList();
    }

    public void save(final T entity){
        entityManager.persist(entity);
    }

    public void update(final T entity){
        entityManager.merge(entity);
    }

    public void delete(final T entity){
        entityManager.remove(entity);
    }

    public void deleteById(final Long entityId ){
        T entity = findById(entityId);
        delete(entity);
    }
}
