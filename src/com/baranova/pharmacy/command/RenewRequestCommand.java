package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.service.RecipeService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command to send request to renew recipe
 */
class RenewRequestCommand implements ICommand {

    /**
     * Provide sending request from user to doctor to renew request
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        long recipeId=Long.parseLong(request.getParameter(ParameterRecipe.RECIPE_ID));
        boolean isExpired= RecipeService.checkIsExpired(recipeId);
        if (!isExpired) {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.RECIPE_NOT_EXPIRED);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean isUpdated= RecipeService.renewRequest(recipeId);
        if (isUpdated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_REQUEST_SUCCESS);
            return PageName.RENEW_REQUEST_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.RENEW_REQUEST_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
