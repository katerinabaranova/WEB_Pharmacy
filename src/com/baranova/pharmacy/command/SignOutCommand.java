package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command for session invalidation
 */
class SignOutCommand implements ICommand {

    /**
     * Invalidate session of current user
     * @param request defines an object to provide client request information to a servlet
     * @return PageName return page of application to be shown to client
     */
    @Override
    public PageName execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PageName.INDEX;
    }
}
