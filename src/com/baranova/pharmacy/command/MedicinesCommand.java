package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Ekaterina on 7/31/16.
 */
public class MedicinesCommand implements ICommand{

    @Override
    public PageName execute(HttpServletRequest request, HttpServletResponse response){
        List<Medicine> medicines= Service.getAllMedicineService();
        if (!medicines.isEmpty()){
            request.setAttribute(AttributeConstant.ALL_MEDICINE_LIST, medicines);
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.MEDICINE_PAGE);
            return PageName.MEDICINE_PAGE;
        } else {
            request.getSession().setAttribute(ParameterName.LAST_PAGE.toString(), PageName.MEDICINE_PAGE);
            return PageName.MEDICINE_PAGE;
        }

    }
}
