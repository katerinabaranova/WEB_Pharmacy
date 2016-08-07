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
 * Class command for filling the list of doctor's recipes.
 */
class DoctorRecipesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request){
        List<Recipe> recipes=ServiceRecipe.findDoctorRecipe(request);
        if (!recipes.isEmpty()){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.RECIPE_DOCTOR_PAGE);
            request.setAttribute(AttributeConstant.RECIPE_LIST,recipes);
            return PageName.RECIPE_DOCTOR_PAGE;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.DOCTOR_RECIPES_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
