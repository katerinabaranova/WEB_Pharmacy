package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ekaterina on 8/26/16.
 */
class RenewRecipeCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        Long recipeId=Long.parseLong(request.getParameter(ParameterRecipe.RECIPE));
        Recipe recipe= ServiceRecipe.renewRecipe(recipeId);
        if (recipe!=null){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_RECIPE_SUCCESS);
            request.getSession().setAttribute(ParameterName.RECIPE,recipe);
            return PageName.RENEW_RECIPE_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.RENEW_REQUEST_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }

    }
}
