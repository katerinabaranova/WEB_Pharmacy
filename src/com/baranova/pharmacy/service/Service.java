package com.baranova.pharmacy.service;


import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Service {
    private static final Logger LOG= LogManager.getLogger();

    public static int checkLogingInfo(String login, String password){
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findEntityByLogin(login);
            String passwordCheck="";
            int role=0;
            if (user.getLogin() != null) {
                passwordCheck=user.getPassword();
                role=user.getRole();
            }
            if (Security.getMD5(password).equals(passwordCheck)){
                return role;
            } else {
                return -1;
            }
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return -1;
    }


}
