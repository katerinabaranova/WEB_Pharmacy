package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterNameUser;
import com.baranova.pharmacy.constant.RoleConstant;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class-service for command classes for making operations with User entity.
 */
public class ServiceUser {

    private static final Logger LOG= LogManager.getLogger();
    public static boolean createUser(HttpServletRequest request){
        User user=new User();
        UserDAO userDAO=new UserDAO();
        user.setLogin(request.getParameter(ParameterNameUser.LOGIN));
        user.setPassword(Security.getMD5(request.getParameter(ParameterNameUser.PASSWORD)));
        user.setName(request.getParameter(ParameterNameUser.NAME));
        user.setSurname(request.getParameter(ParameterNameUser.SURNAME));
        user.setCity(request.getParameter(ParameterNameUser.CITY));
        user.setStreet(request.getParameter(ParameterNameUser.STREET));
        user.setHouseNumber(Integer.parseInt(request.getParameter(ParameterNameUser.HOUSE_NUMBER)));
        user.setApartment(Integer.parseInt(request.getParameter(ParameterNameUser.APARTMENT)));
        user.setEmail(request.getParameter(ParameterNameUser.EMAIL));
        user.setPhoneNumber(request.getParameter(ParameterNameUser.PHONE_NUMBER));
        String role=request.getParameter(ParameterNameUser.ROLE);
        if (RoleConstant.BUYER.equals(role)){
            user.setRole(1);
        } else if (RoleConstant.DOCTOR.equals(role)){
            user.setRole(2);
        }

        boolean userCreated=false;
        try {
            userCreated = userDAO.create(user);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
    public static boolean loginService(HttpServletRequest request){
        String login=request.getParameter(ParameterNameUser.LOGIN);
        String password=request.getParameter(ParameterNameUser.PASSWORD);
        String passwordCheck="";
        long loggedID=0;
        int role=0;
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findEntityByLogin(login);
            if (user.getLogin() != null) {
                passwordCheck=user.getPassword();
                role=user.getRole();
                loggedID=user.getId();
            }
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (Security.getMD5(password).equals(passwordCheck)){
            request.getSession().setAttribute(AttributeConstant.LOGGED_USER,login);
            request.getSession().setAttribute(AttributeConstant.LOGGED_ROLE,role);
            request.getSession().setAttribute(AttributeConstant.LOGGED_ID,loggedID);
            return true;
        } else {
            return false;
        }

    }
}
