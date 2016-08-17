package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.*;
import com.baranova.pharmacy.entity.*;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {

        String n= Security.getMD5("7892347");
        System.out.println(n);
        UserDAO userDAO = new UserDAO();
        User user = new User();
        try {
            user = userDAO.findEntityById(4);
        } catch (DAOException e) {
            LOG.error(e.getMessage());
        }
        System.out.println(user.getAmount());
    }
}
