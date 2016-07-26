package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ekaterina on 7/13/16.
 */
public interface ICommand {
    PageName execute(HttpServletRequest request, HttpServletResponse response);
}
