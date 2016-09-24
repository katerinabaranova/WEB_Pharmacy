package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ekaterina on 7/31/16.
 */
public class PrepareOrderCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        Long medicineID=Long.parseLong(request.getParameter(ParameterName.MEDICINE));
        if (request.getSession().getAttribute(SessionAttribute.LOGGED_USER)==null){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.IMPOSSIBLE_MAKE_ORDER);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return  PageName.ERROR_PAGE;
        }
        Medicine medicine=Service.getMedicineService(medicineID);
        request.setAttribute(SessionAttribute.MEDICINE_FOR_ORDER, medicine);
        request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.ORDER_FORM);
        return PageName.ORDER_FORM;
    }
}
