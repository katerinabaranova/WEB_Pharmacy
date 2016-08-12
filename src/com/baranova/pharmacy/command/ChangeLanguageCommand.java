package com.baranova.pharmacy.command;


import com.baranova.pharmacy.constant.ParameterName;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Class-command for changing  language in application.
 */
class ChangeLanguageCommand implements ICommand {

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
