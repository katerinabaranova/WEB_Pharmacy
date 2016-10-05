package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.*;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {

        RecipeDAO recipeDAO=new RecipeDAO();
        boolean isDeleted=false;
        try {
            List<Recipe> recipe=recipeDAO.findRecipesByDoctor(8);

            System.out.println(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
