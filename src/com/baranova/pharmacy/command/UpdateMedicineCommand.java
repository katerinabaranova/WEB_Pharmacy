package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for update Medicine info in database.
 */
public class UpdateMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        boolean isUpdated= ServiceMedicine.updateMedicineService(request);
        if (isUpdated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.UPDATE_MEDICINE_SUCCESS);
            return PageName.UPDATE_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.UPDATE_MEDICINE_ERROR);
            return PageName.UPDATE_MEDICINE_ERROR;
        }
    }
}
