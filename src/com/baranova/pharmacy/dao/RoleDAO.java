package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.constant.database_constant.RoleTable;
import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.*;
import java.util.List;

/**
 * Class that contains DAO methods for working with "role" table from "pharmacy" schema
 */
public class RoleDAO extends AbstractDAO<Role>{

    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT idrole,roleName FROM pharmacy.role WHERE idrole=?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT idrole,roleName FROM pharmacy.role WHERE roleName=?";

    @Override
    public List<Role> findAll() throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    /**
     * Method establish connection with database for selecting role with specified id from "role" table.
     * @param roleId - specified role id that has to be found.
     * @return Role Object with specified id.
     * @throws DAOException
     */
    @Override
    public Role findEntityById(long roleId) throws DAOException {
        Role role= new Role();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ROLE_BY_ID)){
            st.setLong(1,roleId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            role.setRole(resultSet.getString(RoleTable.ROLE_NAME));
            role.setId(resultSet.getInt(RoleTable.ROLE_ID));
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Role' failed):", e);
        }
        return role;
    }

    /**
     * Method establish connection with database for selecting role with specified name from "role" table.
     * @param name - specified role name that has to be found.
     * @return Role Object with specified name.
     * @throws DAOException
     */
    public Role findEntityByName(String name) throws DAOException {
        Role role= new Role();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ROLE_BY_NAME)){
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                role.setRole(resultSet.getString(RoleTable.ROLE_NAME));
                role.setId(resultSet.getInt(RoleTable.ROLE_ID));
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
