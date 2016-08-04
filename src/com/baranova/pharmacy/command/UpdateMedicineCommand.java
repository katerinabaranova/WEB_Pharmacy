package com.baranova.pharmacy.command;

import com.baranova.pharmacy.service.ServiceMedicine;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for update Medicine info in database.
 */
public class UpdateMedicineCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request){
        boolean isUpdated= ServiceMedicine.updateMedicineService(request);
        if (isUpdated){

        } else {

        }
    }
}
