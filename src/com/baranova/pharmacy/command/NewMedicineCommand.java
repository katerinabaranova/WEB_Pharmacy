package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class-command for adding new medicine to database
 */
class NewMedicineCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request){
        boolean isCreated= ServiceMedicine.newMedicineCreate(request);
        if (isCreated) {
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.NEW_MEDICINE_SUCCESS);
            return PageName.NEW_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.NEW_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }

    }
}
