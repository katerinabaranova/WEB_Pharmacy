package com.baranova.pharmacy.dao;


import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import com.baranova.pharmacy.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<Integer,User>{
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE user.iduser= ?";
    private static final String SQL_SELECT_USER_BY_ROLE= "SELECT iduser,name,surname FROM user WHERE user.fkrole=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.iduser = ?;";

    @Override
    public List<User> findAll() throws ExceptionDAO{
        List<User> users = new ArrayList<>();
        ProxyConnection cn = null;
        Statement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("iduser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("");
        } finally {
            close(st);
        }
        return users;
    }


    @Override
    public User findEntityById(Integer id) {
        User user = new User();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_USER_BY_ID);
            st.setInt(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                user.setUserID(resultSet.getInt("iduser"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            connectionPool.releaseConnection(cn);
        }
        return user;
    }

    @Override
    public boolean delete(Integer id) {

        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
