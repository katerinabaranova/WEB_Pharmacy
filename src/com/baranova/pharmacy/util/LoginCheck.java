package com.baranova.pharmacy.util;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Check if login is being used in database.
 */
public class LoginCheck {
    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO-method to check if there is the same login is being  used in database
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
