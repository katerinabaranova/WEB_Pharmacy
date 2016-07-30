package com.baranova.pharmacy.customtag;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.type.PageName;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by Ekaterina on 7/23/16.
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
            pageContext.setAttribute(AttributeConstant.INCLUDE_TOP_MENU,PageName.MENU_COMMON.getPageName());
        }else if (role == 1) {
            pageContext.setAttribute(AttributeConstant.INCLUDE_TOP_MENU,PageName.MENU_BUYER.getPageName());
        } else if (role==2){
            pageContext.setAttribute(AttributeConstant.INCLUDE_TOP_MENU,PageName.);
        }


        return SKIP_BODY;
    }
}

