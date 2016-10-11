package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.service.OrderService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for filling list of user's orders
 */
class ShowOrdersCommand implements ICommand{

    /**
     * Fill the list uf user(buyer) orders
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        long  userId=Long.parseLong(request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        List<Order> userOrders= OrderService.showOrdersService(userId);
        if (!userOrders.isEmpty()) {
            request.setAttribute(SessionAttribute.ORDER_LIST, userOrders);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.USER_ORDERS);
            return PageName.USER_ORDERS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.ERROR_USER_ORDER);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
