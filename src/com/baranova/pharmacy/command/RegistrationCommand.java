package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterNameUser;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ICommand{
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request){
        User user=new User();
        UserDAO userDAO=new UserDAO();
        user.setLogin(request.getParameter(ParameterNameUser.LOGIN));
        user.setPassword(Security.getMD5(request.getParameter(ParameterNameUser.PASSWORD)));
        user.setName(request.getParameter(ParameterNameUser.NAME));
        user.setSurname(request.getParameter(ParameterNameUser.SURNAME));
        user.setCity(request.getParameter(ParameterNameUser.CITY));
        user.setStreet(request.getParameter(ParameterNameUser.STREET));
        user.setHouseNumber(Integer.parseInt(request.getParameter(ParameterNameUser.HOUSE_NUMBER)));
        user.setApartment(Integer.parseInt(request.getParameter(ParameterNameUser.APARTMENT)));
        user.setEmail(request.getParameter(ParameterNameUser.EMAIL));
        user.setPhoneNumber(request.getParameter(ParameterNameUser.PHONE_NUMBER));
        String role=request.getParameter(ParameterNameUser.ROLE);
        if ("buyer".equals(role)){
            user.setRole(1);
        } else if ("doctor".equals(role)){
            user.setRole(2);
        }
        boolean userCreated=false;
        try {
            userCreated = userDAO.create(user);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (userCreated){
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REGISTRATION_SUCCESS);
            return PageName.REGISTRATION_SUCCESS;
        } else {
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.REGISTRATION_ERROR);
            return PageName.REGISTRATION_ERROR;
        }
    }
}
