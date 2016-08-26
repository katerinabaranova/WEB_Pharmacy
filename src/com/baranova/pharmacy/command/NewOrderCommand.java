package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterOrder;
import com.baranova.pharmacy.service.OrderService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.OrderParametersCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Class command for adding new order to database.
 */
class NewOrderCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        long userID=Long.parseLong(request.getSession().getAttribute("loggedID").toString());
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        parameters.put(ParameterOrder.USER_ID,request.getSession().getAttribute("loggedID").toString());
        boolean payAbility= OrderParametersCheck.checkPayAbility(parameters);
        if (!payAbility) {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.NOT_ENOUGH_MONEY);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean needQuantityAvailable=OrderParametersCheck.checkAvailableQuantity(parameters);
        if (!needQuantityAvailable) {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.NOT_ENOUGH_QUANTITY);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean checkRecipe=OrderParametersCheck.checkRecipe(parameters);
        if (!checkRecipe){
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.NO_RECIPE);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean isCreated= OrderService.newOrderCreate(parameters);
        if (isCreated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ORDER_SUCCESS);
            return PageName.ORDER_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.NEW_ORDER_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
