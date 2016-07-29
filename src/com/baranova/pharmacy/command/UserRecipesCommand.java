package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserRecipesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response) {
        String login=request.getSession().getAttribute("loggedUser").toString();
        List<Recipe> userRecipes= Service.showRecipesService(login);
        if (!userRecipes.isEmpty()) {
            request.setAttribute("recipeList", userRecipes);
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.USER_RECIPES);
            return PageName.USER_RECIPES;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NO_RECIPES_RESULTS);
            return PageName.NO_ORDERS_RESULTS;
        }
    }
}
