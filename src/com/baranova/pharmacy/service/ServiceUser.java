package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterNameUser;
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
 * Class-service for making operations with User entity.
 */
public class ServiceUser {

    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new User
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
                   case ParameterNameUser.LOGIN:
                       user.setLogin(parameter.getValue());
                       break;
                   case ParameterNameUser.PASSWORD:
                       user.setPassword(Security.getMD5(parameter.getValue()));
                       break;
                   case ParameterNameUser.NAME:
                       user.setName(parameter.getValue());
                       break;
                   case ParameterNameUser.SURNAME:
                       user.setSurname(parameter.getValue());
                       break;
                   case ParameterNameUser.CITY:
                       user.setCity(parameter.getValue());
                       break;
                   case ParameterNameUser.STREET:
                       user.setStreet(parameter.getValue());
                       break;
                   case ParameterNameUser.HOUSE_NUMBER:
                       user.setHouseNumber(Integer.parseInt(parameter.getValue()));
                       break;
                   case ParameterNameUser.APARTMENT:
                       user.setApartment(Integer.parseInt(parameter.getValue()));
                       break;
                   case ParameterNameUser.EMAIL:
                       user.setEmail(parameter.getValue());
                       break;
                   case ParameterNameUser.PHONE_NUMBER:
                       user.setPhoneNumber(parameter.getValue());
                       break;
                   case ParameterNameUser.ROLE:
                       Role role = roleDAO.findEntityByName(parameter.getValue());
                       user.setRole(role);
                       break;
               }
           } catch (DAOException e) {
               LOG.error(e.getMessage());
           }
       }
       System.out.println(user);
       boolean userCreated=false;
       try {
           userCreated = userDAO.create(user);
       } catch (DAOException e){
           LOG.error(e.getMessage());
       }
       return userCreated;
    }

    /**
     * Сheck the correctness of the login and password receiving from authorization form
     * @param parameterValues  Map<String,String> that contain name of parameters and theirs value
     * @return User entity  for valid login and password  and null if invalid
     */
    public static User loginService(Map<String,String> parameterValues){
        String login="";
        String password="";
        String passwordCheck="";

        for (Map.Entry<String,String> parameter:parameterValues.entrySet()){
            switch (parameter.getKey()){
                case ParameterNameUser.LOGIN:
                    login=parameter.getValue();
                    break;
                case ParameterNameUser.PASSWORD:
                    password=parameter.getValue();
                    break;
                default:
                    //TODO делать в дефолт
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
}
