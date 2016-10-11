package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterUser;
import com.baranova.pharmacy.dao.RoleDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Class service for making operations with User entity
 */
public class UserService {

    private static final Logger LOG= LogManager.getLogger();

    /**
     * Check if fields "password" and "confirm password" in the registration form appropriate each other
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if "password" and "confirm password" appropriate each other
     */

    public static boolean checkPasswords(Map<String,String> parameters){
        String password="";
        String confirmPassword="";
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()) {
                case ParameterUser.PASSWORD:
                    password = parameter.getValue();
                    break;
                case ParameterUser.CONFIRM_PASSWORD:
                    confirmPassword = parameter.getValue();
                    break;
            }
        }
        return password!=null?password.equals(confirmPassword):false;
    }

    /**
     *  Call DAO method to add new User to database
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of creating was executed
     */

   public static boolean createUser(Map<String,String> parameters){
       User user=new User();
       UserDAO userDAO=new UserDAO();
       RoleDAO roleDAO=new RoleDAO();
       for (Map.Entry<String,String> parameter:parameters.entrySet()) {
           try {
               switch (parameter.getKey()) {
                   case ParameterUser.LOGIN:
                       user.setLogin(parameter.getValue().toLowerCase());
                       break;
                   case ParameterUser.PASSWORD:
                       user.setPassword(Security.getMD5(parameter.getValue()));
                       break;
                   case ParameterUser.NAME:
                       user.setName(parameter.getValue().toLowerCase());
                       break;
                   case ParameterUser.SURNAME:
                       user.setSurname(parameter.getValue().toLowerCase());
                       break;
                   case ParameterUser.CITY:
                       user.setCity(parameter.getValue());
                       break;
                   case ParameterUser.STREET:
                       user.setStreet(parameter.getValue());
                       break;
                   case ParameterUser.HOUSE_NUMBER:
                       user.setHouseNumber(Integer.parseInt(parameter.getValue()));
                       break;
                   case ParameterUser.APARTMENT:
                       user.setApartment(Integer.parseInt(parameter.getValue()));
                       break;
                   case ParameterUser.EMAIL:
                       user.setEmail(parameter.getValue());
                       break;
                   case ParameterUser.PHONE_NUMBER:
                       user.setPhoneNumber(parameter.getValue());
                       break;
                   case ParameterUser.ROLE:
                       Role role = roleDAO.findEntityByName(parameter.getValue());
                       user.setRole(role);
                       break;
               }
           } catch (DAOException e) {
               LOG.error(e.getMessage());
           }
       }
       boolean userCreated=false;
       try {
           userCreated = userDAO.create(user);
       } catch (DAOException e){
           LOG.error(e.getMessage());
       }
       return userCreated;
   }

    /**
     * Check the correctness of the login and password receiving from authorization form
     * @param parameterValues  Map<String,String> that contain name of parameters and theirs value
     * @return User entity  for valid login and password  and null if invalid
     */
    public static User loginService(Map<String,String> parameterValues){
        String login="";
        String password="";
        String passwordCheck="";

        for (Map.Entry<String,String> parameter:parameterValues.entrySet()){
            switch (parameter.getKey()){
                case ParameterUser.LOGIN:
                    login=parameter.getValue().toLowerCase();
                    break;
                case ParameterUser.PASSWORD:
                    password=parameter.getValue();
                    break;
            }
        }
        User user=new User();
        try {
            UserDAO userDAO = new UserDAO();
            user = userDAO.findEntityByLogin(login);
            if (user.getLogin() != null) {
                passwordCheck=user.getPassword();
            }
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (user.getLogin()!=null&&Security.getMD5(password).equals(passwordCheck)){
            return user;
        } else {
            return null;
        }
    }

    /**
     * Call DAO method to refill user balance
     * @param parameterValues  Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if user balance was increased
     */
    public static boolean refillBalance(Map<String,String> parameterValues){
        User user;
        double amount=0;
        UserDAO userDAO=new UserDAO();
        long userId=0;
        for (Map.Entry<String,String> parameter:parameterValues.entrySet()) {
            switch (parameter.getKey()) {
                case ParameterUser.USER_ID:
                    userId=Long.parseLong(parameter.getValue());
                    break;
                case ParameterUser.AMOUNT:
                    amount=Double.parseDouble(parameter.getValue());
                    break;
            }
        }
        boolean isUpdated=false;
        try {
            user=userDAO.findEntityById(userId);
            user.setAmount(user.getAmount()+amount);
            isUpdated=userDAO.update(user);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isUpdated;
    }

    /**
     * Call DAO method to find  user by Id
     * @param userId Id to be checked
     * @return User if user with this id exists, and null if not
     */
    public static User findUserById(String userId){
        User user = null;
        UserDAO userDAO=new UserDAO();
        long id=Long.parseLong(userId);
        try {
            user=userDAO.findEntityById(id);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return user;
    }

    /**
     * Call DAO-method to check if there is the same login is being  used in database
     * @param login string value from registration form
     * @return true if login has been already taken, false if hasn't.
     */

    public static boolean checkLoginUse(String login){
        User user=new User();
        UserDAO userDAO=new UserDAO();
        try {
            user = userDAO.findEntityByLogin(login);
        } catch (DAOException e) {
            LOG.error(e.getMessage());
        }
        return user.getLogin() != null;
    }
}
