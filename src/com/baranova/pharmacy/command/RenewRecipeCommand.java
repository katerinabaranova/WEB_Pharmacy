package com.baranova.pharmacy.command;

import com.baranova.pharmacy.type.PageName;

import javax.servlet.http.HttpServletRequest;

/**
 * Class command to send request to renew recipe.
 */
class RenewRecipeCommand implements ICommand {

    @Override
    public PageName execute(HttpServletRequest request) {
        return null;
    }
}
