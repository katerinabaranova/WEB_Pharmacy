package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command to send request to renew recipe.
 */
class RenewRecipeCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {

        Long id=Long.parseLong(request.getParameter(ParameterRecipe.RECIPE_ID));
        boolean isExpired= ServiceRecipe.checkIsExpired(id);
        if (!isExpired) {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.RECIPE_NOT_EXPIRED);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean isUpdated= ServiceRecipe.renewRequest(id);
        if (isUpdated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RENEW_REQUEST_SUCCESS);
            return PageName.RENEW_REQUEST_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.RENEW_RECIPE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
