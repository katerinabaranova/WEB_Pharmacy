package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class-command for removing  medicine from database.
 */
public class DeleteMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        Long id=Long.parseLong(request.getParameter("medicine"));
        boolean isDeleted= ServiceMedicine.deleteMedicine(id);
        if (isDeleted){
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.DELETE_MEDICINE_SUCCESS);
            return PageName.DELETE_MEDICINE_SUCCESS;
        } else {
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.DELETE_MEDICINE_ERROR);
            return PageName.DELETE_MEDICINE_ERROR;
        }
    }
}
