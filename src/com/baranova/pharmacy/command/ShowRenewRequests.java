package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.RecipeService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command that fill list of renew requests to doctor
 */
class ShowRenewRequests implements ICommand {

    /**
     * Fill the list of renew recipe requests that was sent to doctor by buyers
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        long userId=Long.parseLong(request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        List<Recipe> listRecipes= RecipeService.findDoctorRecipeRequests(userId);
        if (!listRecipes.isEmpty()){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_REQUEST_RECIPES);
            request.setAttribute(SessionAttribute.RECIPE_LIST,listRecipes);
            return PageName.RENEW_REQUEST_RECIPES;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.SHOW_REQUESTS_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
