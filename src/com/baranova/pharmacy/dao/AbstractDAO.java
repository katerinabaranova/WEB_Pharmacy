package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Entity;
import com.baranova.pharmacy.exception.DAOException;

import java.util.List;


abstract class AbstractDAO <T extends Entity> {

    public abstract List<T> findAll () throws DAOException;
    public abstract T findEntityById(long id) throws DAOException;
    public abstract boolean delete(long id) throws DAOException;
    public abstract boolean create(T entity) throws DAOException;
    public abstract boolean update(T entity)throws DAOException;

}
