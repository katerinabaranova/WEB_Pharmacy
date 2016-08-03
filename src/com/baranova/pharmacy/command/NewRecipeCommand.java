package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewRecipeCommand implements ICommand {
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        Recipe recipe=new Recipe();
        RecipeDAO recipeDAO=new RecipeDAO();

        boolean userCreated=false;
        try {
            userCreated = recipeDAO.create(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (userCreated){
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REGISTRATION_SUCCESS);
            return PageName.REGISTRATION_SUCCESS;
        } else {
            return PageName.REGISTRATION_ERROR;
        }
    }
}
