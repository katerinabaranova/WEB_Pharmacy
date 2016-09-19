package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.service.ServiceUser;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        List<String> wrongInputs= PatternCheck.checkRefillForm(parameters);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        parameters.put(ParameterOrder.USER_ID,request.getSession().getAttribute("loggedID").toString());
        boolean isRefilled= ServiceUser.refillBalance(parameters);
        if (isRefilled){
            request.getSession().setAttribute(SessionAttribute.AMOUNT, parameters.get(ParameterUser.AMOUNT));
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.REFILL_BALANCE_SUCCESS);
            return PageName.REFILL_BALANCE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.REFILL_BALANCE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
