package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ekaterina on 7/28/16.
 */
public class ShowOrdersCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response) {
        String login=request.getSession().getAttribute("loggedUser").toString();
        List<Order> userOrders= Service.showOrdersService(login);
        System.out.println(userOrders);
        if (!userOrders.isEmpty()) {
            request.setAttribute("orderList", userOrders);
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.USER_ORDERS);
            return PageName.USER_ORDERS;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NO_ORDERS_RESULTS);
            return PageName.NO_ORDERS_RESULTS;
        }    }
}
