package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterNameUser;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.enum_classes.PageName;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements ICommand{
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response){
        User user=new User();
        UserDAO userDAO=new UserDAO();
        user.setLogin(request.getParameter(ParameterNameUser.LOGIN));
        user.setPassword(request.getParameter(ParameterNameUser.PASSWORD));
        user.setName(request.getParameter(ParameterNameUser.NAME));
        user.setSurname(request.getParameter(ParameterNameUser.SURNAME));
        user.setCity(request.getParameter(ParameterNameUser.CITY));
        user.setStreet(request.getParameter(ParameterNameUser.STREET));
        user.setHouseNumber(Integer.parseInt(request.getParameter(ParameterNameUser.HOUSENUMBER)));
        user.setApartment(Integer.parseInt(request.getParameter(ParameterNameUser.APARTMENT)));
        user.setEmail(request.getParameter(ParameterNameUser.EMAIL));
        user.setPhoneNumber(request.getParameter(ParameterNameUser.PHONENUMBER));
        boolean userCreated=false;
        try {
            userCreated = userDAO.create(user);
        } catch (ExceptionDAO e){
            LOG.error(e.getMessage());
        }
        if (userCreated){
            return PageName.REGISTRATION_SUCCESS;
        } else {
            return PageName.REGISTRATION_ERROR;
        }
    }
}
