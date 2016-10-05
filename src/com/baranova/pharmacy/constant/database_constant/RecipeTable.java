package com.baranova.pharmacy.constant.database_constant;

/**
 * Names of columns of "recipe" table  in  "pharmacy" schema
 */
public class RecipeTable {

    public static final String RECIPE_ID="idrecipe";
    public static final String DATE="date";
    public static final String FK_DOCTOR="fkDoctor";
    public static final String FK_PATIENT="fkPatient";
    public static final String FK_MEDICINE="fkMedicine";
    public static final String MEDICINE_QUANTITY="medicineQuantity";
    public static final String EXPIRED="expired";
    public static final String RENEW_REQUEST="renewRequest";
}
