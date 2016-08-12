package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.OrderDAO;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.dao.RoleDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.Role;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        Role role=new Role();
        try {
            RoleDAO roleDAO=new RoleDAO();
            role=roleDAO.findEntityByName("buyer");
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        System.out.println(role);
    }
}
