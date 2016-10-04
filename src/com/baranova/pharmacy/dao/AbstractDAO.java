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
     * Returns list containing all of the elements from the definite table of the database.
     * @return List <Entity>.
     * @throws DAOException
     */
    public abstract List<T> findAll () throws DAOException;

    /**
     * Returns Entity object with specified id from the definite table of the database.
     * @param id - number that define entity id that should be found.
     * @return Entity Object with specified id.
     * @throws DAOException
     */
    public abstract T findEntityById(long id) throws DAOException;

    /**
     * Delete Entity object with specified id from the definite table of the database.
     * @param id number that define entity id that should be delete.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean delete(long id) throws DAOException;

    /**
     * Add Entity object to the definite table of the database.
     * @param entity to be added to database.
     * @return true if operation of creating was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean create(T entity) throws DAOException;

    /**
     * Update Entity object in the definite table of the database.
     * @param entity to be updated.
     * @return true if operation of updating was executed, false - if wasn't.
     * @throws DAOException
     */
    public abstract boolean update(T entity)throws DAOException;

}
