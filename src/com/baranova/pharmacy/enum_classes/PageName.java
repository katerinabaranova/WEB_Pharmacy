package com.baranova.pharmacy.enum_classes;

/**
 * Created by Ekaterina on 7/18/16.
 */
public enum PageName {

    USER_PAGE ("jsp/loginsuccess.jsp"),
    ERROR_LOGING("jsp/error_login_page.jsp");

    private String pageName;


    PageName(String pageName) {
        this.pageName=pageName;
    }


    public String getPageName() {
        return pageName;
    }

}
