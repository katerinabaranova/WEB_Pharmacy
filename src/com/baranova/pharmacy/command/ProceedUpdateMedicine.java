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

    @Override
    public PageName execute (HttpServletRequest request){
        Long medicineId=Long.parseLong(request.getParameter(ParameterMedicine.MEDICINE));
        Medicine medicine= MedicineService.getMedicine(medicineId);
        request.getSession().setAttribute(ParameterMedicine.MEDICINE,medicine);
        request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.PROCEED_UPDATE);
        return PageName.PROCEED_UPDATE;
    }
}
