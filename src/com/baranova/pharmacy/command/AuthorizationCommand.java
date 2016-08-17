package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.service.ServiceUser;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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
        User user= ServiceUser.loginService(parameterValues);
        if (user!=null){
            request.getSession().setAttribute(AttributeConstant.LOGGED_USER,user.getLogin());
            request.getSession().setAttribute(AttributeConstant.LOGGED_ROLE,user.getRole().getId());
            request.getSession().setAttribute(AttributeConstant.LOGGED_ID,user.getId());
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.USER_PAGE);
            return PageName.USER_PAGE;
        } else {
            LOG.error("Wrong login/password");
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.LOGIN_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
