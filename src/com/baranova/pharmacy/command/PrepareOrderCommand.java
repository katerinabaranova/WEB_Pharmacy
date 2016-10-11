package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for preparing user order.
 */
class PrepareOrderCommand implements ICommand {

    /**
     * Prepare new user order for convenient ordering
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        long medicineId=Long.parseLong(request.getParameter(ParameterName.MEDICINE));
        if (request.getSession().getAttribute(SessionAttribute.LOGGED_USER_ID)==null){
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.IMPOSSIBLE_MAKE_ORDER);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return  PageName.ERROR_PAGE;
        }
        Medicine medicine= MedicineService.findMedicine(medicineId);
        request.setAttribute(SessionAttribute.MEDICINE_FOR_ORDER, medicine);
        request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ORDER_FORM);
        return PageName.ORDER_FORM;
    }
}
