package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchCommand implements ICommand  {
    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response) {
        String medicineName=request.getParameter("medicineName");
        List<Medicine> medicines= Service.searchService(medicineName);
        if (!medicines.isEmpty()) {
            request.setAttribute("medicineList", medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.SEARCH_RESULTS);
            return PageName.SEARCH_RESULTS;
        }
        return PageName.NO_SEARCH_RESULTS;
    }
}
