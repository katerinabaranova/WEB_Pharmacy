package com.baranova.pharmacy.util;

import com.baranova.pharmacy.constant.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Check correctness of forms input.
 */
public class PatternCheck {
    /**
     * Check if some input appropriate definite pattern
     * @param pattern that is defined for input
     * @param input  String form input
     * @return boolean value if input appropriate pattern
     */

    private static boolean checkPatternAppropriateness(String pattern,String input){
        Pattern p1=Pattern.compile(pattern);
        Matcher m1=p1.matcher(input);
        return m1.matches();
    }

    /**
     * Check correctness of filling authorization form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkAuthorizationForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterUser.LOGIN:
                    if (!checkPatternAppropriateness(Patterns.LOGIN,parameter.getValue())){
                        wrongFields.add(ParameterUser.LOGIN);
                    }
                    break;
                case ParameterUser.PASSWORD:
                    if (!checkPatternAppropriateness(Patterns.PASSWORD,parameter.getValue())){
                        wrongFields.add(ParameterUser.PASSWORD);
                    }
                    break;
            }
        }
        return wrongFields;
    }

    /**
     * Check correctness of filling refill balance form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkRefillForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterUser.AMOUNT:
                    if (!checkPatternAppropriateness(Patterns.INCREASE_AMOUNT,parameter.getValue())){
                        wrongFields.add(ParameterUser.AMOUNT);
                    }
                    break;
            }
        }
        return wrongFields;
    }

    /**
     * Check correctness of filling new medicine form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkMedicineForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterMedicine.MEDICINE_NAME:
                    if (!checkPatternAppropriateness(Patterns.MEDICINE_NAME,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.MEDICINE_NAME);
                    }
                    break;
                case ParameterMedicine.MEDICINE_DOSAGE:
                    if (!checkPatternAppropriateness(Patterns.DOSAGE,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.MEDICINE_DOSAGE);
                    }
                    break;
                case ParameterMedicine.MEDICINE_PACKAGE:
                    if (!checkPatternAppropriateness(Patterns.MEDICINE_PACKAGE,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.MEDICINE_PACKAGE);
                    }
                    break;
                case ParameterMedicine.PACK_QUANTITY:
                    if (!checkPatternAppropriateness(Patterns.QUANTITY,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.PACK_QUANTITY);
                    }
                    break;
                case ParameterMedicine.IN_STORE_QUANTITY:
                    if (!checkPatternAppropriateness(Patterns.QUANTITY,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.IN_STORE_QUANTITY);
                    }
                    break;
                case  ParameterMedicine.PRICE:
                    if (!checkPatternAppropriateness(Patterns.PRICE,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.PRICE);
                    }
                    break;
            }
        }
        return wrongFields;
    }

    /**
     * Check correctness of filling new recipe form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkRecipeForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterRecipe.MEDICINE_NAME:
                    if (!checkPatternAppropriateness(Patterns.MEDICINE_NAME,parameter.getValue())){
                        wrongFields.add(ParameterRecipe.MEDICINE_NAME);
                    }
                    break;
                case ParameterRecipe.MEDICINE_DOSAGE:
                    if (!checkPatternAppropriateness(Patterns.DOSAGE,parameter.getValue())){
                        wrongFields.add(ParameterRecipe.MEDICINE_DOSAGE);
                    }
                    break;
                case ParameterRecipe.NAME:
                    if (!checkPatternAppropriateness(Patterns.PATIENT_NAME,parameter.getValue())){
                        wrongFields.add(ParameterRecipe.NAME);
                    }
                    break;
                case ParameterRecipe.SURNAME:
                    if (!checkPatternAppropriateness(Patterns.PATIENT_SURNAME,parameter.getValue())){
                        wrongFields.add(ParameterRecipe.SURNAME);
                    }
                    break;
                case ParameterRecipe.MEDICINE_QUANTITY:
                    if (!checkPatternAppropriateness(Patterns.RECIPE_MEDICINE_QUANTITY,parameter.getValue())){
                        wrongFields.add(ParameterRecipe.MEDICINE_QUANTITY);
                    }
                    break;
            }
        }
        return wrongFields;
    }


    /**
     * Check correctness of filling registration form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkRegistrationForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterUser.LOGIN:
                    if (!checkPatternAppropriateness(Patterns.LOGIN,parameter.getValue())){
                        wrongFields.add(ParameterUser.LOGIN);
                    }
                    break;
                case ParameterUser.PASSWORD:
                    if (!checkPatternAppropriateness(Patterns.PASSWORD,parameter.getValue())){
                        wrongFields.add(ParameterUser.PASSWORD);
                    }
                    break;
                case ParameterUser.CONFIRM_PASSWORD:
                    if (!checkPatternAppropriateness(Patterns.PASSWORD,parameter.getValue())){
                        wrongFields.add(ParameterUser.CONFIRM_PASSWORD);
                    }
                    break;
                case ParameterUser.NAME:
                    if (!checkPatternAppropriateness(Patterns.PATIENT_NAME,parameter.getValue())){
                        wrongFields.add(ParameterUser.NAME);
                    }
                    break;
                case ParameterUser.SURNAME:
                    if (!checkPatternAppropriateness(Patterns.PATIENT_SURNAME,parameter.getValue())){
                        wrongFields.add(ParameterUser.SURNAME);
                    }
                    break;
                case ParameterUser.CITY:
                    if (!checkPatternAppropriateness(Patterns.CITY,parameter.getValue())){
                        wrongFields.add(ParameterUser.CITY);
                    }
                    break;
                case ParameterUser.STREET:
                    if (!checkPatternAppropriateness(Patterns.STREET,parameter.getValue())){
                        wrongFields.add(ParameterUser.STREET);
                    }
                    break;
                case ParameterUser.HOUSE_NUMBER:
                    if (!checkPatternAppropriateness(Patterns.HOUSENUMBER,parameter.getValue())){
                        wrongFields.add(ParameterUser.HOUSE_NUMBER);
                    }
                    break;
                case ParameterUser.APARTMENT:
                    if (!checkPatternAppropriateness(Patterns.APARTMENT,parameter.getValue())){
                        wrongFields.add(ParameterUser.APARTMENT);
                    }
                    break;
                case ParameterUser.EMAIL:
                    if (!checkPatternAppropriateness(Patterns.EMAIL,parameter.getValue())){
                        wrongFields.add(ParameterUser.EMAIL);
                    }
                    break;
                case ParameterUser.PHONE_NUMBER:
                    if (!checkPatternAppropriateness(Patterns.PHONENUMBER,parameter.getValue())){
                        wrongFields.add(ParameterUser.PHONE_NUMBER);
                    }
                    break;
            }
        }
        return wrongFields;
    }

    /**
     * Check correctness of filling new order form
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return List of field, that doesn't appropriate theirs pattern
     */
    public static List<String> checkOrderForm (Map<String,String> parameters){
        List<String>  wrongFields=new ArrayList<>();
        for (Map.Entry<String,String> parameter:parameters.entrySet()){
            switch (parameter.getKey()){
                case ParameterMedicine.MEDICINE_NAME:
                    if (!checkPatternAppropriateness(Patterns.MEDICINE_NAME,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.MEDICINE_NAME);
                    }
                    break;
                case ParameterMedicine.MEDICINE_DOSAGE:
                    if (!checkPatternAppropriateness(Patterns.DOSAGE,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.MEDICINE_DOSAGE);
                    }
                    break;
                case ParameterOrder.PRICE:
                    if (!checkPatternAppropriateness(Patterns.PRICE,parameter.getValue())){
                        wrongFields.add(ParameterOrder.PRICE);
                    }
                    break;
                case ParameterOrder.QUANTITY:
                    if (!checkPatternAppropriateness(Patterns.QUANTITY,parameter.getValue())){
                        wrongFields.add(ParameterMedicine.PACK_QUANTITY);
                    }
                    break;
            }
        }
        return wrongFields;
    }

}
