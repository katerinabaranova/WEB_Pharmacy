package com.baranova.pharmacy.servlet;

import com.baranova.pharmacy.command.CommandHelper;
import com.baranova.pharmacy.command.ICommand;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.enum_classes.PageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ekaterina on 7/18/16.
 */
public class Controller extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandName = request.getParameter(ParameterName.COMMAND);
        ICommand command = CommandHelper.getCommand(commandName);
        PageName pageName = command.execute(request, response);
        request.getRequestDispatcher(pageName.toString()).forward(request, response);
    }
}
