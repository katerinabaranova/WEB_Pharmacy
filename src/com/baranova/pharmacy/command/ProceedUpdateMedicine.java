package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterMedicine;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for preparing Medicine update.
 */
public class ProceedUpdateMedicine implements ICommand {

    @Override
    public PageName execute (HttpServletRequest request){
        Long id=Long.parseLong(request.getParameter(ParameterMedicine.MEDICINE));
        Medicine medicine= ServiceMedicine.getMedicineService(id);
        request.setAttribute(ParameterMedicine.MEDICINE,medicine);
        request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.PROCCED_UPDATE);
        return PageName.PROCCED_UPDATE;
    }
}
