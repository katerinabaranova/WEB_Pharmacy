package com.baranova.pharmacy.customtag;

import com.baranova.pharmacy.constant.SessionAttribute;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Choose navigation menu for type of user .
 */
@SuppressWarnings("serial")
public class ChooseMenuTag extends TagSupport {

    private int role;

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        if (role==0){
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_COMMON.getPageName());
        }else if (role == 1) {
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_BUYER.getPageName());
        } else if (role==2){
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_DOCTOR.getPageName());
        } else if (role==3){
            pageContext.setAttribute(SessionAttribute.INCLUDE_TOP_MENU,PageName.MENU_PHARMACIST.getPageName());
        }
        return SKIP_BODY;
    }
}

