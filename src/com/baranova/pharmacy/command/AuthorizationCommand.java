package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.service.UserService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Class command for user authorization.
 */
class AuthorizationCommand implements ICommand {

    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameterValues=requestContent.getRequestParameters();
        List<String> wrongInputs= PatternCheck.checkAuthorizationForm(parameterValues);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        User user= UserService.loginService(parameterValues);
        if (user!=null){
            request.getSession().setAttribute(SessionAttribute.LOGGED_USER,user.getLogin());
            request.getSession().setAttribute(SessionAttribute.LOGGED_ROLE,user.getRole().getRole());
            request.getSession().setAttribute(SessionAttribute.LOGGED_ID,user.getId());
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.USER_PAGE);
            return PageName.USER_PAGE;
        } else {
            LOG.error("Wrong login/password");
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.LOGIN_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
