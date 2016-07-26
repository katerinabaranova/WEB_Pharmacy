package com.baranova.pharmacy.type;

/**
 * Created by Ekaterina on 7/18/16.
 */
public enum PageName {

    INDEX("index.jsp"),
    LOGGING_FORM("loginform.jsp"),
    LOGGING_ERROR("error_login_page.jsp"),
    USER_PAGE ("loginsuccess.jsp"),
    REGISTRATION_FORM("registration_form.jsp"),
    REGISTRATION_SUCCESS("registration_success.jsp"),
    REGISTRATION_ERROR("error_registration_page.jsp"),
    MENU_BUYER("top_menu_buyer.jsp");

    private String pageName;

    PageName(String pageName) {
        this.pageName=pageName;
    }

    public String getPageName() {
        return pageName;
    }

}
