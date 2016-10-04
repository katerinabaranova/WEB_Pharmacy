package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.RecipeService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for renew recipe.
 */
class RenewRecipeCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        Long recipeId=Long.parseLong(request.getParameter(ParameterRecipe.RECIPE));
        Recipe recipe= RecipeService.renewRecipe(recipeId);
        if (recipe!=null){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_RECIPE_SUCCESS);
            request.getSession().setAttribute(ParameterName.RECIPE,recipe);
            return PageName.RENEW_RECIPE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.RENEW_REQUEST_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
