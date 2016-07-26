package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterNameUser;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.service.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AutorizationCommand implements ICommand {
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response){
        String login=request.getParameter(ParameterNameUser.LOGIN);
        String password=request.getParameter(ParameterNameUser.PASSWORD);
        int role;
        if ((role=Service.checkLogingInfo(login,password))>0){
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser",login);
            session.setAttribute("loggedRole",role);
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.USER_PAGE);
            return PageName.USER_PAGE;
        } else {
            LOG.error("Wrong login/password");
            return PageName.LOGGING_ERROR;
        }
    }
}
