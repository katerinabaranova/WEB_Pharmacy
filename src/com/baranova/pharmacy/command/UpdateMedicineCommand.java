package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for update Medicine info in database.
 */
class UpdateMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        boolean isUpdated= ServiceMedicine.updateMedicineService(request);
        if (isUpdated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.UPDATE_MEDICINE_SUCCESS);
            return PageName.UPDATE_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.UPDATE_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
