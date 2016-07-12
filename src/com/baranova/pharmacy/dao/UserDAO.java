package com.baranova.pharmacy.dao;


import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.factory.EntityFactory;
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
    private static final String SQL_SELECT_ALL_USERS = "SELECT iduser,login,name,surname,email,phonenumber,city,street,housenumber FROM user";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT iduser,login,name,surname,email,phonenumber,city,street,houseNumber FROM user WHERE user.iduser=?";
    private static final String SQL_SELECT_USER_BY_ROLE= "SELECT iduser,login,name,surname,email,phonenumber,city,street,houseNumber FROM user WHERE user.fkrole=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.iduser = ?;";
    private static final String SQL_CREATE_USER = "INSERT INTO user(iduser,login,password,name,surname,email,phonenumber,city,street,housenumber,fkrole) values(?,?,?,?,?,?,?,?,?,?,?);";
    @Override
    public List<User> findAll() throws ExceptionDAO{
        List<User> users = new ArrayList<>();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("iduser"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
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
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
            connectionPool.releaseConnection(cn);
        }
        return user;
    }

    public List<User> findEntityByRole(Integer roleId) {
        List<User> users = new ArrayList<>();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_USER_BY_ROLE);
            st.setInt(1,roleId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getInt("iduser"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return users;
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
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_CREATE_USER);
            st.setLong(1,entity.getUserID());
            st.setString(2,entity.getLogin());
            st.setString(3,entity.getPassword());
            st.setString(4,entity.getName());
            st.setString(5,entity.getSurname());
            st.setString(6,entity.getEmail());
            st.setString(7,entity.getPhoneNumber());
            st.setString(8,entity.getCity());
            st.setString(9,entity.getStreet());
            st.setInt(10,entity.getHouseNumber());
            st.setInt(11,entity.getRole());
            isCreated=st.execute();
        } catch (SQLException e) {
            System.err.println("SQL exception (request or table failed): " + e);
        } finally {
            close(st);
        }
        return isCreated;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
