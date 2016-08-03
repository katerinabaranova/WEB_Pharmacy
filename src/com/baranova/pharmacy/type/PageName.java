package com.baranova.pharmacy.type;

/**
 * Created by Ekaterina on 7/18/16.
 */
public enum PageName {

    INDEX("jsp/index.jsp"),
    LOGGING_FORM("jsp/loginform.jsp"),
    LOGGING_ERROR("jsp/error_login_page.jsp"),
    MEDICINE_PAGE("jsp/medicine_control/medicine_page.jsp"),
    MENU_BUYER("jsp/top_menu/top_menu_buyer.jsp"),
    MENU_COMMON("jsp/top_menu/top_menu_main.jsp"),
    MENU_DOCTOR("jsp/top_menu/top_menu_doctor.jsp"),
    MENU_PHARMACIST("jsp/top_menu/top_menu_pharmacist.jsp"),
    NEW_MEDICINE_ERROR("jsp/medicine_control/new_medicine_error.jsp"),
    NEW_MEDICINE_FORM("jsp/medicine_control/new_medicine_form.jsp"),
    NEW_MEDICINE_SUCCESS("jsp/medicine_control/new_medicine_success.jsp"),
    NO_MEDICINE_PAGE("jsp/medicine_control/no_medicine_page.jsp"),
    NO_ORDERS_RESULTS("jsp/no_user_orders.jsp"),
    NO_RECIPES_RESULTS("jsp/no_user_recipes.jsp"),
    NO_SEARCH_RESULTS("jsp/no_search_results.jsp"),
    ORDER_FORM("jsp/order_form.jsp"),
    REGISTRATION_FORM("jsp/registration_form.jsp"),
    REGISTRATION_SUCCESS("jsp/registration_success.jsp"),
    REGISTRATION_ERROR("jsp/error_registration_page.jsp"),
    SIGN_OUT("jsp/sign_out.jsp"),
    SEARCH_PAGE("jsp/search_page.jsp"),
    SEARCH_RESULTS("jsp/search_results.jsp"),USER_ORDERS("jsp/user_orders.jsp"),
    USER_PAGE ("jsp/loginsuccess.jsp"),
    USER_RECIPES("jsp/user_recipes.jsp");

    private String pageName;

    PageName(String pageName) {
        this.pageName=pageName;
    }

    public String getPageName() {
        return pageName;
    }

}
