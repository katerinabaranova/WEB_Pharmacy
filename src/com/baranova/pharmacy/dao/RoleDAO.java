package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.*;
import java.util.List;

/**
 *
 */
public class RoleDAO extends AbstractDAO<Role>{

    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT idrole,roleName FROM pharmacy.role WHERE idrole=?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT idrole,roleName FROM pharmacy.role WHERE roleName=?";

    @Override
    public List<Role> findAll() throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    @Override
    public Role findEntityById(long id) throws DAOException {
        Role role= new Role();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ROLE_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                role.setRole(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("idrole"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Role' failed):", e);
        }
        return role;
    }

    public Role findEntityByName(String name) throws DAOException {
        Role role= new Role();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ROLE_BY_NAME)){
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                role.setRole(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("idrole"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Role' failed):", e);
        }
        return role;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    @Override
    public boolean create(Role entity) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    @Override
    public boolean update(Role entity) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }
}
