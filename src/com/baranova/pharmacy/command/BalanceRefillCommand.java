package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.service.UserService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Class command for refill user amount
 */
class BalanceRefillCommand implements ICommand {

    /**
     * Execute refill of user amount
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
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
        parameters.put(ParameterUser.USER_ID,request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        boolean isRefilled= UserService.refillBalance(parameters);
        User user=UserService.findUserById(request.getSession().getAttribute(SessionAttribute.LOGGED_ID).toString());
        if (isRefilled){
            if (user!=null){
                request.getSession().setAttribute(SessionAttribute.LOGGED_USER_OBJECT, user);
            }
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
