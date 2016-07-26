package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Entity;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public abstract class AbstractDAO <T extends Entity> {
    private static final Logger LOG= LogManager.getLogger();

    public abstract List<T> findAll () throws DAOException;
    public abstract T findEntityById(long id) throws DAOException;
    public abstract boolean delete(long id) throws DAOException;
    public abstract boolean delete(T entity) throws DAOException;
    public abstract boolean create(T entity) throws DAOException;
    public abstract boolean update(T entity)throws DAOException;

}
