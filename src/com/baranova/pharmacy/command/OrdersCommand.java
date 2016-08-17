package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.service.OrderService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for filling list of user's orders.
 */
class OrdersCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request) {
        Long  userID=Long.parseLong(request.getSession().getAttribute("loggedID").toString());
        List<Order> userOrders= OrderService.showOrdersService(userID);
        if (!userOrders.isEmpty()) {
            request.setAttribute("orderList", userOrders);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.USER_ORDERS);
            return PageName.USER_ORDERS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.ERROR_USER_ORDER);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
