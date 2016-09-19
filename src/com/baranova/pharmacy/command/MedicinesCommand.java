package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class that fill list of pharmacy medicines.
 */
class MedicinesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request){
        List<Medicine> medicines= Service.getAllMedicineService();
        if (!medicines.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.ALL_MEDICINE_LIST, medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.MEDICINE_PAGE);
            return PageName.MEDICINE_PAGE;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NO_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }

    }
}
