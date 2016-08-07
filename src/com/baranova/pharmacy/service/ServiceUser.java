package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterNameUser;
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
