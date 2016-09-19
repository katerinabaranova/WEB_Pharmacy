package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command that fill list of User recipes.
 */
class BuyerRecipesCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        Long doctorID=Long.parseLong(request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        List<Recipe> recipes= ServiceRecipe.findBuyerRecipe(doctorID);
        if (!recipes.isEmpty()){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RECIPE_BUYER_PAGE);
            request.getSession().setAttribute(SessionAttribute.RECIPE_LIST,recipes);
            return PageName.RECIPE_BUYER_PAGE;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.ERROR_USER_RECIPE);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
