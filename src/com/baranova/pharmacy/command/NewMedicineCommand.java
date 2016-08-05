package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class-command for adding new medicine to database
 */
public class NewMedicineCommand implements ICommand{

    /**
     *
     * @param request
     * @return
     */
    @Override
    public PageName execute(HttpServletRequest request){
        boolean isCreated= ServiceMedicine.newMedicineCreate(request);

        if (isCreated) {
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NEW_MEDICINE_SUCCESS);
            return PageName.NEW_MEDICINE_SUCCESS;
        } else {
            HttpSession session=request.getSession();
            session.setAttribute(ParameterName.LAST_PAGE.toString(), PageName.NEW_MEDICINE_ERROR);
            return PageName.NEW_MEDICINE_ERROR;
        }

    }
}