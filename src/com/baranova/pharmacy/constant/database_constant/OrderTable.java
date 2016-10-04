package com.baranova.pharmacy.constant.database_constant;

/**
 * Names of columns of "order" table  in  "pharmacy" schema
 */
public class OrderTable {

    public static final String ORDER_ID="idorder";
    public static final String BUYER="fkBuyer";
    public static final String MEDICINE="fkMedicine";
    public static final String QUANTITY="quantity";
    public static final String TOTAL_AMOUNT="totalAmount";
    public static final String DELIVERY="delivery";
}
