package com.baranova.pharmacy.customtag;

import com.baranova.pharmacy.constant.RoleConstant;
import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Choose navigation menu for appropriate type of user.
 */
@SuppressWarnings("serial")
public class ChooseMenuTag extends TagSupport {

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        if (RoleConstant.BUYER.equalsIgnoreCase(role)) {
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_BUYER.getPageName());
        } else if (RoleConstant.DOCTOR.equalsIgnoreCase(role)){
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_DOCTOR.getPageName());
        } else if (RoleConstant.PHARMACIST.equalsIgnoreCase(role)){
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_PHARMACIST.getPageName());
        } else {
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_COMMON.getPageName());
        }
        return SKIP_BODY;
    }
}

