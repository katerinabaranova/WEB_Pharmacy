package com.baranova.pharmacy.dao;


import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import com.baranova.pharmacy.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User>{

    private static final String SQL_SELECT_ALL_USERS = "SELECT U.iduser,U.login,U.name,U.surname,U.email,U.phonenumber,U.city,U.street,U.housenumber,U.apartment,R.idrole,R.roleName, U.amount FROM user U INNER JOIN role R ON U.fkrole=R.idrole";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT U.iduser,U.login,U.name,U.surname,U.email,U.phonenumber,U.city,U.street,U.housenumber,U.apartment,R.idrole,R.roleName, U.amount FROM user U INNER JOIN role R ON U.fkrole=R.idrole WHERE U.iduser=?";
    private static final String SQL_SELECT_USER_BY_ROLE= "SELECT iduser,login,name,surname,email,phonenumber,city,street,houseNumber,apartment,amount FROM user WHERE user.fkrole=?";
    private static final String SQL_SELECT_USER_BY_SURNAME_NAME="SELECT iduser FROM user WHERE user.surname=? AND user.name=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.iduser = ?;";
    private static final String SQL_CREATE_USER = "INSERT INTO user(login,password,name,surname,email,phonenumber,city,street,housenumber,apartment,fkrole) values(?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER_BY_ENTITY="UPDATE user SET login=?,name=?,surname=?,email=?,phonenumber=?,city=?,street=?,housenumber=?,apartment=?,fkrole=?,amount=? WHERE iduser=?;";
    private static final String SQL_SELECT_USER_BY_LOGIN ="SELECT U.iduser,U.login,U.password,R.idrole,R.roleName FROM user U INNER JOIN role R ON U.fkrole=R.idrole WHERE U.login=?";

    @Override
    public List<User> findAll() throws DAOException {
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
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                user.setApartment(resultSet.getInt("apartment"));
                Role role=new Role();
                role.setId(resultSet.getLong("idrole"));
                role.setRole(resultSet.getString("roleName"));
                user.setRole(role);
                user.setAmount(resultSet.getInt("amount"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return users;
    }

    @Override
    public User findEntityById(long id) throws DAOException {
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
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                user.setApartment(resultSet.getInt("apartment"));
                Role role=new Role();
                role.setId(resultSet.getLong("idrole"));
                user.setRole(role);
                user.setAmount(resultSet.getInt("amount"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):" , e);
        }
        return user;
    }

    public List<User> findEntityByRole(long roleId) throws DAOException {
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
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phonenumber"));
                user.setCity(resultSet.getString("city"));
                user.setStreet(resultSet.getString("street"));
                user.setHouseNumber(resultSet.getInt("housenumber"));
                user.setApartment(resultSet.getInt("apartment"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):" , e);
        }
        return users;
    }

    public User findEntityByLogin(String login) throws DAOException {
        User user=new User();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_LOGIN)){
            st.setString(1,login.toLowerCase());
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                user.setLogin(resultSet.getString("login"));
                user.setId(resultSet.getLong("iduser"));
                user.setPassword(resultSet.getString("password"));
                Role role=new Role();
                role.setId(resultSet.getLong("idrole"));
                role.setRole(resultSet.getString("roleName"));
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return user;
    }

    public User findEntityNameSurname(String surname, String name) throws DAOException{
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        User user=new User();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_SURNAME_NAME)){
            st.setString(1,surname.toLowerCase());
            st.setString(2,name.toLowerCase());
            ResultSet resultSet=st.executeQuery();
            while (resultSet.next()){
                user.setUserID(resultSet.getLong("iduser"));
            }
        } catch (SQLException e){
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return user;
    }
    @Override
    public boolean delete(long id) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_USER_BY_ID)){
            st.setLong(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):",e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(User entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(User entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_USER)){
            st.setString(1,entity.getLogin().toLowerCase());
            st.setString(2,entity.getPassword());
            st.setString(3,entity.getName().toLowerCase());
            st.setString(4,entity.getSurname().toLowerCase());
            st.setString(5,entity.getEmail());
            st.setString(6,entity.getPhoneNumber());
            st.setString(7,entity.getCity());
            st.setString(8,entity.getStreet());
            st.setInt(9,entity.getHouseNumber());
            st.setInt(10,entity.getApartment());
            st.setLong(11,entity.getRole().getId());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request or table 'User' failed):",e);
        }
        return isCreated;
    }

    @Override
    public boolean update(User entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_USER_BY_ENTITY)){
            st.setString(1,entity.getLogin());
            st.setString(2,entity.getName());
            st.setString(3,entity.getSurname());
            st.setString(4,entity.getEmail());
            st.setString(5,entity.getPhoneNumber());
            st.setString(6,entity.getCity());
            st.setString(7,entity.getStreet());
            st.setInt(8,entity.getHouseNumber());
            st.setInt(9,entity.getApartment());
            st.setLong(10,entity.getRole().getId());
            st.setInt(11,entity.getAmount());
            st.setLong(12,entity.getUserID());
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return isUpdate;
    }
}
