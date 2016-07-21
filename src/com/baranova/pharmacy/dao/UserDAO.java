package com.baranova.pharmacy.dao;


import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.factory.EntityFactory;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import com.baranova.pharmacy.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User>{

    private static final String SQL_SELECT_ALL_USERS = "SELECT iduser,login,name,surname,email,phonenumber,city,street,housenumber,apartment FROM user";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT iduser,login,name,surname,email,phonenumber,city,street,houseNumber,apartment FROM user WHERE user.iduser=?";
    private static final String SQL_SELECT_USER_BY_ROLE= "SELECT iduser,login,name,surname,email,phonenumber,city,street,houseNumber,apartment FROM user WHERE user.fkrole=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.iduser = ?;";
    private static final String SQL_CREATE_USER = "INSERT INTO user(iduser,login,password,name,surname,email,phonenumber,city,street,housenumber,apartment,fkrole) values(?,?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER_BY_ENTITY="UPDATE user SET login=?,password=?,name=?,surname=?,email=?,phonenumber=?,city=?,street=?,housenumber=?,apartment=?,fkrole=? WHERE iduser=?;";
    private static final String SQL_SELECT_PASSWORD_BY_LOGIN="SELECT login,password,fkrole FROM user WHERE login=?";

    @Override
    public List<User> findAll() throws ExceptionDAO{
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_USERS)){
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
                user.setApartment(resultSet.getInt("apartment"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return users;
    }

    @Override
    public User findEntityById(long id) throws ExceptionDAO{
        User user = new User();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                user.setUserID(resultSet.getLong("iduser"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                user.setApartment(resultSet.getInt("apartment"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return user;
    }

    public List<User> findEntityByRole(long roleId) throws ExceptionDAO{
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_ROLE)){
            st.setLong(1,roleId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUserID(resultSet.getLong("iduser"));
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                user.setApartment(resultSet.getInt("apartment"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return users;
    }

    public User findEntityByLogin(String login) throws ExceptionDAO{
        User user=new User();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_PASSWORD_BY_LOGIN)){
            st.setString(1,login.toLowerCase());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getInt("fkrole"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return user;
    }

    @Override
    public boolean delete(long id) throws ExceptionDAO{
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_USER_BY_ID)){
            st.setLong(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(User entity) throws ExceptionDAO {
        return false;
    }

    @Override
    public boolean create(User entity) throws ExceptionDAO {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_USER)){
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
            st.setInt(11,entity.getApartment());
            st.setInt(12,entity.getRole());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isCreated;
    }

    @Override
    public boolean update(User entity) throws ExceptionDAO {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_USER_BY_ENTITY)){
            st.setString(1,entity.getLogin());
            st.setString(2,entity.getPassword());
            st.setString(3,entity.getName());
            st.setString(4,entity.getSurname());
            st.setString(5,entity.getEmail());
            st.setString(6,entity.getPhoneNumber());
            st.setString(7,entity.getCity());
            st.setString(8,entity.getStreet());
            st.setInt(9,entity.getHouseNumber());
            st.setInt(10,entity.getApartment());
            st.setInt(11,entity.getRole());
            st.setLong(12,entity.getUserID());
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isUpdate;
    }
}
