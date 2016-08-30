package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.service.ServiceUser;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Class command increase user amount.
 */
class BalanceRefillCommand implements ICommand {


    @Override
    public PageName execute(HttpServletRequest request) {
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        parameters.put(ParameterOrder.USER_ID,request.getSession().getAttribute("loggedID").toString());
        boolean isRefilled= ServiceUser.refillBalance(parameters);
        if (isRefilled){
            request.getSession().setAttribute(AttributeConstant.AMOUNT, parameters.get(ParameterUser.AMOUNT));
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.REFILL_BALANCE_SUCCESS);
            return PageName.REFILL_BALANCE_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.REFILL_BALANCE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
