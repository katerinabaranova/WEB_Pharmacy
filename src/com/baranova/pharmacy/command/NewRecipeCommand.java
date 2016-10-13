package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.service.RecipeService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 *  Class command for adding new recipe to database.
 */

class NewRecipeCommand implements ICommand {

    /**
     * Execute adding new recipe to database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameterValues=requestContent.getRequestParameters();
        List<String> wrongInputs= PatternCheck.checkRecipeForm(parameterValues);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        if (!MedicineService.necessaryRecipeMedicine(parameterValues)){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NOT_NECESSARY_RECIPE);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        Recipe recipe= RecipeService.createNewRecipe(parameterValues);
        if (recipe!=null){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.NEW_RECIPE_SUCCESS);
            request.getSession().setAttribute(ParameterName.RECIPE,recipe);
            return PageName.NEW_RECIPE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NEW_RECIPE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
