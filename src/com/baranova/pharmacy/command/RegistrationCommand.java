package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterUser;
import com.baranova.pharmacy.service.ServiceUser;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.LoginCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Class-command for adding new User to database.
 */
class RegistrationCommand implements ICommand{
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters= requestContent.getRequestParameters();
        boolean loginFree= LoginCheck.checkLoginUse(parameters.get(ParameterUser.LOGIN));
        if (!loginFree){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.LOGIN_IN_USE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean checkPasswords=ServiceUser.checkPasswords(parameters);
        if (!checkPasswords){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.WRONG_PASSWORDS);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean userCreated= ServiceUser.createUser(parameters);
        if (userCreated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.REGISTRATION_SUCCESS);
            return PageName.REGISTRATION_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.REGISTRATION_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
