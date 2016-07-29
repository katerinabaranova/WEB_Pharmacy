package com.baranova.pharmacy.type;

/**
 * Created by Ekaterina on 7/18/16.
 */
public enum PageName {

    INDEX("jsp/index.jsp"),
    LOGGING_FORM("jsp/loginform.jsp"),
    LOGGING_ERROR("jsp/error_login_page.jsp"),
    USER_PAGE ("jsp/loginsuccess.jsp"),
    REGISTRATION_FORM("jsp/registration_form.jsp"),
    REGISTRATION_SUCCESS("jsp/registration_success.jsp"),
    REGISTRATION_ERROR("jsp/error_registration_page.jsp"),
    SEARCH_RESULTS("jsp/search_results.jsp"),
    NO_SEARCH_RESULTS("jsp/no_search_results.jsp"),
    MENU_BUYER("jsp/top_menu/top_menu_buyer.jsp"),
    USER_ORDERS("jsp/user_orders.jsp"),
    USER_RECIPES("jsp/user_recipes.jsp"),
    SEARCH_PAGE("jsp/search_page.jsp"),
    NO_ORDERS_RESULTS("jsp/no_user_orders.jsp"),
    MENU_COMMON("jsp/top_menu/top_menu_main.jsp"),
    NO_RECIPES_RESULTS("jsp/no_user_recipes.jsp");
    private String pageName;

    PageName(String pageName) {
        this.pageName=pageName;
    }

    public String getPageName() {
        return pageName;
    }

}
