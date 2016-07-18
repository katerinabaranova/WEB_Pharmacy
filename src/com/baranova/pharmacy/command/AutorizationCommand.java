package com.baranova.pharmacy.command;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.enum_classes.PageName;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AutorizationCommand implements ICommand {
    private static final Logger LOG= LogManager.getLogger();

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println("vxcvxcv");
            response.setContentType("text/html");
            request.getRequestDispatcher("loginform.jsp").include(request,response);
            String login=request.getParameter("Login");
            String password=request.getParameter("Password");
            UserDAO userDAO=new UserDAO();
            User user=userDAO.findEntityByLogin(login);
            if (user!=null && user.getPassword().equals(password)){
                HttpSession session = request.getSession();
                session.setAttribute("loggedUser",login);
                request.getRequestDispatcher("/login_success.jsp").forward(request, response);
            } else {
                LOG.error("Wrong login/password");
                request.getRequestDispatcher("/error_login_page.jsp").forward(request,response);
            }
        } catch (ServletException|IOException|ExceptionDAO e){
            LOG.error(e.getMessage());
        }
        return null;
    }
}
