package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceUser;
import com.baranova.pharmacy.type.PageName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

class AuthorizationCommand implements ICommand {
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        boolean isLogged= ServiceUser.loginService(request);
        if (isLogged){
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
