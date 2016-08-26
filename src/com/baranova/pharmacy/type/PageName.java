package com.baranova.pharmacy.type;

/**
 * List of application pages.
 */
public enum PageName {

    DELETE_MEDICINE_SUCCESS("jsp/medicine_control/delete_medicine_success.jsp"),
    INDEX("jsp/index.jsp"),
    ERROR_PAGE("jsp/error_page/error_page.jsp"),
    LOGGING_FORM("jsp/user_control/loginform.jsp"),
    MEDICINE_PAGE("jsp/medicine_control/medicine_page.jsp"),
    MENU_BUYER("jsp/top_menu/top_menu_buyer.jsp"),
    MENU_COMMON("jsp/top_menu/top_menu_main.jsp"),
    MENU_DOCTOR("jsp/top_menu/top_menu_doctor.jsp"),
    MENU_PHARMACIST("jsp/top_menu/top_menu_pharmacist.jsp"),
    NEW_MEDICINE_FORM("jsp/medicine_control/new_medicine_form.jsp"),
    NEW_MEDICINE_SUCCESS("jsp/medicine_control/new_medicine_success.jsp"),
    NEW_RECIPE_FORM("jsp/recipe_control/recipe_form.jsp"),
    NO_SEARCH_RESULTS("jsp/no_search_results.jsp"),
    ORDER_FORM("jsp/order_control/order_form.jsp"),
    ORDER_SUCCESS("jsp/order_control/order_success.jsp"),
    RECIPE_BUYER_PAGE("jsp/recipe_control/recipes_buyer.jsp"),
    RECIPE_DOCTOR_PAGE("jsp/recipe_control/recipes_doctor.jsp"),
    REGISTRATION_FORM("jsp/registration_form.jsp"),
    REGISTRATION_SUCCESS("jsp/registration_success.jsp"),
    RENEW_REQUEST_RECIPES("jsp/recipe_control/recipes_requests_doctor.jsp"),
    RENEW_REQUEST_SUCCESS("jsp/recipe_control/renew_request_success.jsp"),
    SIGN_OUT("jsp/sign_out.jsp"),
    SEARCH_PAGE("jsp/search_page.jsp"),
    SEARCH_RESULTS("jsp/search_results.jsp"),
    PROCCED_UPDATE("jsp/medicine_control/procced_update.jsp"),
    UPDATE_MEDICINE_SUCCESS("jsp/medicine_control/update_medicine_success.jsp"),
    USER_ORDERS("jsp/order_control/user_orders.jsp"),
    USER_PAGE ("jsp/user_control/loginsuccess.jsp");


    private String pageName;

    PageName(String pageName) {
        this.pageName=pageName;
    }

    public String getPageName() {
        return pageName;
    }

}
