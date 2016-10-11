package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;
import com.baranova.pharmacy.util.PatternCheck;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Class command for adding new medicine to database
 */
class NewMedicineCommand implements ICommand{

    /**
     * Execute adding new medicine to database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        List<String> wrongInputs= PatternCheck.checkMedicineForm(parameters);
        if (!wrongInputs.isEmpty()){
            request.getSession().setAttribute(SessionAttribute.WRONG_INPUTS,wrongInputs);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.WRONG_INPUT_PAGE);
            return  PageName.WRONG_INPUT_PAGE;
        }
        boolean isCreated= MedicineService.newMedicineCreate(parameters);
        if (isCreated) {
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.NEW_MEDICINE_SUCCESS);
            return PageName.NEW_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.NEW_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE, PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }

    }
}
