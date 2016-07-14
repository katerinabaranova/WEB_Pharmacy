package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Entity;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public abstract class AbstractDAO <K,T extends Entity> {
    private static final Logger LOG= LogManager.getLogger();

    public abstract List<T> findAll () throws ExceptionDAO;
    public abstract T findEntityById(K id) throws ExceptionDAO;
    public abstract boolean delete(K id) throws ExceptionDAO;
    public abstract boolean delete(T entity) throws ExceptionDAO;
    public abstract boolean create(T entity) throws ExceptionDAO;
    public abstract boolean update(T entity)throws ExceptionDAO;
    //TODO Зачем эти методы в дао
    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
           LOG.error("Impossible to close statement");
        }
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOG.error("Connection pool error");
        }
    }
}
