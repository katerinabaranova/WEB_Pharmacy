package com.baranova.pharmacy.servlet;

import com.baranova.pharmacy.command.CommandHelper;
import com.baranova.pharmacy.command.ICommand;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/Servlet")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(ParameterName.COMMAND);
        ICommand command = CommandHelper.getCommand(commandName);
        PageName pageName = command.execute(request);
        request.getRequestDispatcher(pageName.getPageName()).forward(request, response);
    }
}
