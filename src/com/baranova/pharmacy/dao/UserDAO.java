package com.baranova.pharmacy.dao;


import com.baranova.pharmacy.connection.ConnectionPool;
import com.baranova.pharmacy.connection.ProxyConnection;
import com.baranova.pharmacy.constant.FileConstant;
import com.baranova.pharmacy.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserDAO extends AbstractDAO<Integer,User>{
    public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SQL_SELECT_USER_BY_ROLE=
            "SELECT iduser,name,surname FROM user WHERE role=?";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        try {
            ResourceBundle resource = ResourceBundle.getBundle(FileConstant.DATABASE_INFO);
            int size = Integer.parseInt(resource.getString("db.poolsize"));
            ConnectionPool<ProxyConnection> connectionPool=ConnectionPool.getInstance(size);
            cn = connectionPool.getConnection();
            st = cn.createStatement();
            ResultSet resultSet = st.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("userid"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);

            // код возвращения экземпляра Connection в пул();
        }
        return users;
    }


    @Override
    public User findEntityById(Integer id) {
        return null;
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
