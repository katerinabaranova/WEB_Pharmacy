package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class command for changing  language in application
 */
class ChangeLanguageCommand implements ICommand {

    /**
     * Provide opportunity to change language on application pages
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String local = request.getParameter(ParameterName.LANGUAGE);
        session.setAttribute(ParameterName.LANGUAGE, local);
        PageName lastPage = (PageName) session.getAttribute(ParameterName.LAST_PAGE);
        if (lastPage == null) {
            return PageName.INDEX;
        } else {
            return lastPage;
        }
    }
}
