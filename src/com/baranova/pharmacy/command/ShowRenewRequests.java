package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Command to show renew requests to doctor.
 */
class ShowRenewRequests implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        Long id=Long.parseLong(request.getSession().getAttribute(AttributeConstant.LOGGED_ID).toString());
        List<Recipe> listRecipes= ServiceRecipe.findDoctorRecipeRequests(id);
        if (!listRecipes.isEmpty()){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_REQUEST_RECIPES);
            request.setAttribute(AttributeConstant.RECIPE_LIST,listRecipes);
            return PageName.RENEW_REQUEST_RECIPES;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.SHOW_REQUESTS_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
