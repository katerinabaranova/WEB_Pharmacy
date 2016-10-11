package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.RecipeService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for renew recipe
 */
class RenewRecipeCommand implements ICommand {

    /**
     * Add new recipe to database based on old recipe
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        long recipeId=Long.parseLong(request.getParameter(ParameterRecipe.RECIPE));
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
