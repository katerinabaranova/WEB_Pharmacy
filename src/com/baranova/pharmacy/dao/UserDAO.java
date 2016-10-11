package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.constant.database_constant.RoleTable;
import com.baranova.pharmacy.constant.database_constant.UserTable;
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

/**
 * Class that contains DAO methods for working with "user" table from "pharmacy" schema

 */
public class UserDAO extends AbstractDAO<User>{

    private static final String SQL_SELECT_ALL_USERS = "SELECT U.iduser,U.login,U.name,U.surname,U.email,U.phonenumber,U.city,U.street,U.housenumber,U.apartment,R.idrole,R.roleName, U.amount FROM user U INNER JOIN role R ON U.fkrole=R.idrole";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT U.iduser,U.login,U.name,U.surname,U.email,U.phonenumber,U.city,U.street,U.housenumber,U.apartment,R.idrole,R.roleName, U.amount FROM user U INNER JOIN role R ON U.fkrole=R.idrole WHERE U.iduser=?";
    private static final String SQL_SELECT_USER_BY_SURNAME_NAME="SELECT iduser FROM user WHERE user.surname=? AND user.name=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE user.iduser = ?;";
    private static final String SQL_CREATE_USER = "INSERT INTO user(login,password,name,surname,email,phonenumber,city,street,housenumber,apartment,fkrole) values(?,?,?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_USER_BY_ENTITY="UPDATE user SET login=?,name=?,surname=?,email=?,phonenumber=?,city=?,street=?,housenumber=?,apartment=?,fkrole=?,amount=? WHERE iduser=?;";
    private static final String SQL_SELECT_USER_BY_LOGIN ="SELECT U.iduser,U.login,U.password,U.amount,U.fkRole, R.idrole,R.roleName FROM user U INNER JOIN role R ON U.fkRole=R.idrole WHERE U.login=?";


    /**
     * Method establish connection with database for selecting all users from  "user" table.
     * @return List <User> containing all of the elements from the table.
     * @throws DAOException
     */
    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_USERS)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(UserTable.USER_ID));
                user.setLogin(resultSet.getString(UserTable.LOGIN));
                user.setName(resultSet.getString(UserTable.NAME));
                user.setSurname(resultSet.getString(UserTable.SURNAME));
                user.setEmail(resultSet.getString(UserTable.EMAIL));
                user.setPhoneNumber(resultSet.getString(UserTable.PHONENUMBER));
                user.setCity(resultSet.getString(UserTable.CITY));
                user.setStreet(resultSet.getString(UserTable.STREET));
                user.setHouseNumber(resultSet.getInt(UserTable.HOUSENUMBER));
                user.setApartment(resultSet.getInt(UserTable.APARTMENT));
                Role role=new Role();
                role.setId(resultSet.getLong(RoleTable.ROLE_ID));
                role.setRole(resultSet.getString(RoleTable.ROLE_NAME));
                user.setRole(role);
                user.setAmount(resultSet.getInt(UserTable.AMOUNT));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return users;
    }

    /**
     * Method establish connection with database for selecting user with specified id from "user" table.
     * @param userId - specified user id that has to be found.
     * @return User Object with specified id.
     * @throws DAOException
     */
    @Override
    public User findEntityById(long userId) throws DAOException {
        User user = new User();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_ID)){
            st.setLong(1,userId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setId(resultSet.getLong(UserTable.USER_ID));
            user.setLogin(resultSet.getString(UserTable.LOGIN));
            user.setName(resultSet.getString(UserTable.NAME));
            user.setSurname(resultSet.getString(UserTable.SURNAME));
            user.setEmail(resultSet.getString(UserTable.EMAIL));
            user.setPhoneNumber(resultSet.getString(UserTable.PHONENUMBER));
            user.setCity(resultSet.getString(UserTable.CITY));
            user.setStreet(resultSet.getString(UserTable.STREET));
            user.setHouseNumber(resultSet.getInt(UserTable.HOUSENUMBER));
            user.setApartment(resultSet.getInt(UserTable.APARTMENT));
            Role role=new Role();
            role.setId(resultSet.getLong(RoleTable.ROLE_ID));
            role.setRole(resultSet.getString(RoleTable.ROLE_NAME));
            user.setRole(role);
            user.setAmount(resultSet.getInt(UserTable.AMOUNT));
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):" , e);
        }
        return user;
    }


    /**
     * Method establish connection with database for selecting user with specified login from "user" table.
     * @param login - specified user login that has to be found.
     * @return User Object with specified login.
     * @throws DAOException
     */
    public User findEntityByLogin(String login) throws DAOException {
        User user=new User();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_LOGIN)){
            st.setString(1,login.toLowerCase());
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setLogin(resultSet.getString(UserTable.LOGIN));
            user.setId(resultSet.getLong(UserTable.USER_ID));
            user.setPassword(resultSet.getString(UserTable.PASSWORD));
            user.setAmount(resultSet.getDouble(UserTable.AMOUNT));
            Role role=new Role();
            role.setId(resultSet.getLong(RoleTable.ROLE_ID));
            role.setRole(resultSet.getString(RoleTable.ROLE_NAME));
            user.setRole(role);
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return user;
    }
    /**
     * Method establish connection with database for selecting user with specified name and surname from "user" table.
     * @param surname - specified user surname that has to be found.
     * @param name - specified user name that has to be found.
     * @return User Object with specified login.
     * @throws DAOException
     */
    public User findEntityNameSurname(String surname, String name) throws DAOException{
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        User user=new User();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_USER_BY_SURNAME_NAME)){
            st.setString(1,surname.toLowerCase());
            st.setString(2,name.toLowerCase());
            ResultSet resultSet=st.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong(UserTable.USER_ID));
            }
        } catch (SQLException e){
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return user;
    }

    /**
     * Method establish connection with database for deleting user with specified id from "user" table.
     * @param userId number that define user entity id that should be deleted.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean delete(long userId) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_USER_BY_ID)){
            st.setLong(1,userId);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):",e);
        }
        return isDeleted;
    }

    /**
     * Method establish connection with database to add new user to "user" table.
     * @param entity - user to be added to database.
     * @return true if operation of adding was executed, false - if wasn't.
     * @throws DAOException
     */
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
            throw new DAOException("Impossible to execute request(request or table 'User' failed):",e);
        }
        return isCreated;
    }

    /**
     * Method establish connection with database to update user entity in "user" table.
     * @param entity - user to be updated.
     * @return true if operation of update was executed, false - if wasn't.
     * @throws DAOException
     */
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
            st.setDouble(11,entity.getAmount());
            st.setLong(12,entity.getId());
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'User' failed):", e);
        }
        return isUpdate;
    }
}
