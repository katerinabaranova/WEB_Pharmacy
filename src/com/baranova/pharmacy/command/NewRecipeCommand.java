package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

class NewRecipeCommand implements ICommand {
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        boolean isCreated= ServiceRecipe.createNewRecipe(parameters);
        if (isCreated){
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE, PageName.REGISTRATION_SUCCESS);
            return PageName.REGISTRATION_SUCCESS;
        } else {
            return PageName.ERROR_PAGE;
        }
    }
}
