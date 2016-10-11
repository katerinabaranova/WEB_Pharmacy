package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.ParameterOrder;
import com.baranova.pharmacy.service.OrderService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.OrderParametersCheck;
import com.baranova.pharmacy.util.PatternCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Class command for adding new order to database
 */
class NewOrderCommand implements ICommand {

    /**
     * Execute adding new order to database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        parameters.put(ParameterOrder.USER_ID,request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        List<String> wrongInputs= PatternCheck.checkOrderForm(parameters);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        boolean payAbility= OrderParametersCheck.checkPayAbility(parameters);
        if (!payAbility) {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NOT_ENOUGH_MONEY);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean needQuantityAvailable=OrderParametersCheck.checkAvailableQuantity(parameters);
        if (!needQuantityAvailable) {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NOT_ENOUGH_QUANTITY);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean checkRecipe=OrderParametersCheck.checkRecipe(parameters);
        if (!checkRecipe){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NO_RECIPE);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
        boolean isCreated= OrderService.newOrderCreate(parameters);
        if (isCreated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ORDER_SUCCESS);
            return PageName.ORDER_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NEW_ORDER_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
