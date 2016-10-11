package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterUser;
import com.baranova.pharmacy.service.UserService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Class command for adding new User to database
 */
class RegistrationCommand implements ICommand{

    /**
     * Execute adding new user to database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters= requestContent.getRequestParameters();
        List<String> wrongInputs= PatternCheck.checkRegistrationForm(parameters);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        boolean loginTaken= UserService.checkLoginUse(parameters.get(ParameterUser.LOGIN));
        if (loginTaken){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.LOGIN_IN_USE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean checkPasswords= UserService.checkPasswords(parameters);
        if (!checkPasswords){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.WRONG_PASSWORDS);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean userCreated= UserService.createUser(parameters);
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
