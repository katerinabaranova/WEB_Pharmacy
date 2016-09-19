package com.baranova.pharmacy.constant;

/**
 * Patterns to check the correctness of forms inputs.
 */
public class Patterns {
    public static final String LOGIN="[A-Za-z0-9]{5,15}";
    public static final String PASSWORD="[A-Za-z0-9]{5,15}";
    public static final String INCREASE_AMOUNT="[1-9][\\d]?";
    public static final String MEDICINE_NAME="[\\w\\-]{4,20}";
    public static final String DOSAGE="[1-9][\\d]{0,6}";
    public static final String QUANTITY="[1-9][\\d]{0,4}";
    public static final String MEDICINE_PACKAGE ="[\\w\\-]{4,20}";
    public static final String PRICE="\\d+(\\.\\d{2})?";
    public static final String PATIENT_NAME="[A-Za-zА-Яа-я]{2,20}";
    public static final String PATIENT_SURNAME="[A-Za-zА-Яа-я\\-]{2,20}";
    public static final String RECIPE_MEDICINE_QUANTITY="[1-9][1}";





}
