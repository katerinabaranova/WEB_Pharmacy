package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Entity;
import com.baranova.pharmacy.exception.DAOException;

import java.util.List;

/**
 * The root interface in the DAO hierarchy.
 * @param <T>
 */
abstract class AbstractDAO <T extends Entity> {

    /**
     * Method establish connection with database for selecting all entities from definite table of the database.
     * @return List <Entity> containing all of the elements from the definite table.
     * @throws DAOException
     */
    public abstract List<T> findAll () throws DAOException;


    /**
     * Method establish connection with database for selecting entity with specified id from definite table.
     * @param id - specified entity id that has to be found.
     * @return Entity Object with specified id.
     * @throws DAOException
     */
    public abstract T findEntityById(long id) throws DAOException;


    /**
     * Method establish connection with database to delete entity with specified id from definite table.
     * @param id number that define entity id that should be deleted.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean delete(long id) throws DAOException;


    /**
     * Method establish connection with database to add new entity to definite table.
     * @param entity to be added to database.
     * @return true if operation of adding was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean create(T entity) throws DAOException;


    /**
     * Method establish connection with database to update entity in definite table.
     * @param entity to be updated.
     * @return true if operation of updating was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean update(T entity)throws DAOException;

}
