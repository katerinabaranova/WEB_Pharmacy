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
 * Class command that fill list of User recipes.
 */
class BuyerRecipesCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        Long doctorID=Long.parseLong(request.getSession().getAttribute(AttributeConstant.LOGGED_ID).toString());
        List<Recipe> recipes= ServiceRecipe.findBuyerRecipe(doctorID);
        if (!recipes.isEmpty()){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RECIPE_BUYER_PAGE);
            request.setAttribute(AttributeConstant.RECIPE_LIST,recipes);
            return PageName.RECIPE_BUYER_PAGE;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.ERROR_USER_RECIPE);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
