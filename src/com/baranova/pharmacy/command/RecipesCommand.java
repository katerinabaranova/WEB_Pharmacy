package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for filling the list of buyer's recipes
 */
public class RecipesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request) {
        String login=request.getSession().getAttribute(AttributeConstant.LOGGED_USER).toString();
        List<Recipe> userRecipes= Service.showRecipesService(login);
        if (!userRecipes.isEmpty()) {
            request.setAttribute(AttributeConstant.RECIPE_LIST, userRecipes);
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.USER_RECIPES);
            return PageName.USER_RECIPES;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NO_RECIPES_RESULTS);
            return PageName.NO_ORDERS_RESULTS;
        }
    }
}
