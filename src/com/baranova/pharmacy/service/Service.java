package com.baranova.pharmacy.service;


import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Ekaterina on 7/20/16.
 */
public class Service {
    private static final Logger LOG= LogManager.getLogger();

    public static boolean checkLogingInfo(String login, String password){
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findEntityByLogin(login);
            if (user.getLogin() != null && user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (ExceptionDAO e){
            LOG.error(e.getMessage());
        }
        return false;
    }
}
