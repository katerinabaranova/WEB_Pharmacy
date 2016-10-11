package com.baranova.pharmacy.command;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.constant.ErrorPageMessage;
import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.service.MedicineService;
import com.baranova.pharmacy.service.SessionRequestContent;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Class command for update Medicine info in database.
 */
class UpdateMedicineCommand implements ICommand {

    /**
     * Provide medicine update in database
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request){
        SessionRequestContent requestContent=new SessionRequestContent();
        requestContent.extractValues(request);
        Map<String,String> parameters=requestContent.getRequestParameters();
        boolean isUpdated= MedicineService.updateMedicineService(parameters);
        if (isUpdated){
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.UPDATE_MEDICINE_SUCCESS);
            return PageName.UPDATE_MEDICINE_SUCCESS;
        } else {
            request.getSession().setAttribute(SessionAttribute.ERROR_MESSAGE, ErrorPageMessage.UPDATE_MEDICINE_ERROR);
            request.getSession().setAttribute(ParameterName.LAST_PAGE,PageName.ERROR_PAGE);
            return PageName.ERROR_PAGE;
        }
    }
}
