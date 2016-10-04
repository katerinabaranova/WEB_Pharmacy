package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command clear for session invalidation.
 */
class SignOutCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PageName.INDEX;
    }
}
