package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.OrderDAO;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        try {
            RecipeDAO orderDAO=new RecipeDAO();
            Recipe r2=orderDAO.findEntityById(1);
            System.out.println("ff"+r2);
            Recipe recipe=new Recipe(1,4,8,1,35,false);
            boolean f=orderDAO.create(recipe);
            //System.out.println(f);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
