package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekaterina on 7/14/16.
 */
public class RoleDAO extends AbstractDAO<Integer,Role>{
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT idrole,roleName FROM role WHERE idrole=?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT idrole,roleName FROM role WHERE roleName=?";


    @Override
    public List<Role> findAll() throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }

    @Override
    public Role findEntityById(Integer id) throws ExceptionDAO {
        Role role= new Role();
        ProxyConnection cn=null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_ROLE_BY_ID);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                role.setRole(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("idrole"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return role;
    }

    public Role findEntityByName(String name) throws ExceptionDAO {
        Role role= new Role();
        ProxyConnection cn=null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_ROLE_BY_NAME);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                role.setRole(resultSet.getString("roleName"));
                role.setId(resultSet.getInt("idrole"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return role;
    }

    @Override
    public boolean delete(Integer id) throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }

    @Override
    public boolean delete(Role entity) throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }

    @Override
    public boolean create(Role entity) throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }

    @Override
    public boolean update(Role entity) throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }
}
