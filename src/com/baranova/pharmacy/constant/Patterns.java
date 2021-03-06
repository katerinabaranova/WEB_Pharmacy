package com.baranova.pharmacy.constant;

/**
 * Patterns to check the correctness of forms inputs.
 */
public class Patterns {
    public static final String LOGIN="[A-Za-z0-9]{5,15}";
    public static final String PASSWORD="[A-Za-z0-9]{5,15}";
    public static final String INCREASE_AMOUNT="([0-9]+)([.]{1})([0-9]{0,2})";
    public static final String MEDICINE_NAME="[\\w\\- ]{4,20}";
    public static final String DOSAGE="[1-9][\\d]{0,6}";
    public static final String QUANTITY="[1-9][\\d]{0,4}";
    public static final String MEDICINE_PACKAGE ="[\\w\\-]{4,20}";
    public static final String PRICE="([0-9]+)([.]{1})([0-9]{0,2})?";
    public static final String PATIENT_NAME="[A-Za-zА-Яа-я]{2,20}";
    public static final String PATIENT_SURNAME="[A-Za-zА-Яа-я\\-]{2,20}";
    public static final String RECIPE_MEDICINE_QUANTITY="[1-9]{1}";
    public static final String CITY="[A-Za-zА-Яа-я \\-]{2,20}";
    public static final String STREET="[A-Za-zА-Яа-я\\- ]{2,20}";
    public static final String HOUSENUMBER="[1-9][0-9]{0,4}";
    public static final String APARTMENT="[1-9][0-9]{0,4}";
    public static final String EMAIL="([\\w\\-.]+)@(\\w+\\.)([a-z]{2,4})";
    public static final String PHONENUMBER="^(\\+375\\-|8\\-0)(17|29|44|25|33)\\-\\d{3}\\-\\d{2}\\-\\d{2}$";
}
