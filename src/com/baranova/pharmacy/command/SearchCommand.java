package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.*;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for medicine search
 */

class SearchCommand implements ICommand  {

    /**
     * Provide search of specified medicine in database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        String medicineName=request.getParameter(ParameterMedicine.MEDICINE_NAME);
        List<Medicine> medicines= MedicineService.searchService(medicineName);
        if (!medicines.isEmpty()&& !RoleConstant.DOCTOR.equalsIgnoreCase(request.getSession().getAttribute(SessionAttribute.LOGGED_ROLE).toString()))
        {
            request.getSession().setAttribute(SessionAttribute.MEDICINE_LIST, medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.SEARCH_RESULTS);
            return PageName.SEARCH_RESULTS;
        } else if ((!medicines.isEmpty()&& RoleConstant.DOCTOR.equalsIgnoreCase(request.getSession().getAttribute(SessionAttribute.LOGGED_ROLE).toString())))
        {
            request.getSession().setAttribute(SessionAttribute.MEDICINE_LIST, medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.SEARCH_RESULTS_DOCTOR);
            return PageName.SEARCH_RESULTS_DOCTOR;
        }
        else
        {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NO_SEARCH_RESULTS);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
