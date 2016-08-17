package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *Class command for medicine search
 */

class SearchCommand implements ICommand  {
    @Override
    public PageName execute(HttpServletRequest request) {
        String medicineName=request.getParameter("medicineName");
        List<Medicine> medicines= Service.searchService(medicineName);
        if (!medicines.isEmpty()) {
            request.setAttribute("medicineList", medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.SEARCH_RESULTS);
            return PageName.SEARCH_RESULTS;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.NO_SEARCH_RESULTS);
            return PageName.NO_SEARCH_RESULTS;
        }
    }
}
