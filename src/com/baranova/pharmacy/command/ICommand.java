package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * The root interface in the command hierarchy.
 */
public interface ICommand {

    /**
     * Prepare answer for application's client
     * @param request defines an object to provide client request information to a servlet
     * @return target PageName
     */
    PageName execute(HttpServletRequest request);
}
