package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ErrorPageConstant;
import com.baranova.pharmacy.constant.ParameterMedicine;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class-command for removing  medicine from database.
 */
class DeleteMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        Long id=Long.parseLong(request.getParameter(ParameterMedicine.MEDICINE));
        boolean isDeleted= ServiceMedicine.deleteMedicine(id);
        if (isDeleted){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.DELETE_MEDICINE_SUCCESS);
            return PageName.DELETE_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(AttributeConstant.ERROR_MESSAGE, ErrorPageConstant.DELETE_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
