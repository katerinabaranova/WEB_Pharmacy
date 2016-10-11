package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterMedicine;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for preparing Medicine update.
 */
class ProceedUpdateMedicine implements ICommand {

    /**
     * Prepare new medicine for convenient adding to database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute (HttpServletRequest request){
        long medicineId=Long.parseLong(request.getParameter(ParameterMedicine.MEDICINE));
        Medicine medicine= MedicineService.findMedicine(medicineId);
        request.getSession().setAttribute(ParameterMedicine.MEDICINE,medicine);
        request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.PROCEED_UPDATE);
        return PageName.PROCEED_UPDATE;
    }
}
