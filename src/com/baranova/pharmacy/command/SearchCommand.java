package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class command for medicine search
 */

class SearchCommand implements ICommand  {
    @Override
    public PageName execute(HttpServletRequest request) {
        String medicineName=request.getParameter("medicineName");
        List<Medicine> medicines= MedicineService.searchService(medicineName);
        if (!medicines.isEmpty()) {
            request.getSession().setAttribute(SessionAttribute.MEDICINE_LIST, medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.SEARCH_RESULTS);
            return PageName.SEARCH_RESULTS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NO_SEARCH_RESULTS);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
