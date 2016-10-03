package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterMedicine;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class-command for removing  medicine from database.
 */
class DeleteMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        Long id=Long.parseLong(request.getParameter(ParameterMedicine.MEDICINE));
        boolean isDeleted= MedicineService.deleteMedicine(id);
        if (isDeleted){
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.DELETE_MEDICINE_SUCCESS);
            return PageName.DELETE_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.DELETE_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
