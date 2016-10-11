package com.baranova.pharmacy.util;

import com.baranova.pharmacy.constant.ParameterOrder;
import com.baranova.pharmacy.constant.ServiceCost;
import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * Class check necessary parameters for creating new order.
 */
public class OrderParametersCheck {

    private static final Logger LOG= LogManager.getLogger();


    /**
     * Check if there is enough money on user amount to pay for order.
     * @param parameters Map<String,String> that contain name of necessary parameters and theirs value.
     * @return true if user has enough money to make order, false if hasn't.
     */
    public static boolean checkPayAbility(Map<String,String> parameters) {
        long userId=0;
        double orderAmount=1;
        boolean delivery=false;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.PRICE:
                    orderAmount*=Double.parseDouble(parameter.getValue());
                    break;
                case ParameterOrder.QUANTITY:
                    orderAmount*=Integer.parseInt(parameter.getValue());
                    break;
                case ParameterOrder.DELIVERY:
                    delivery=Boolean.parseBoolean(parameter.getValue());
                    break;
                case ParameterOrder.USER_ID:
                    userId=Long.parseLong(parameter.getValue());
                    break;
            }
        }
        if (delivery) {
            orderAmount+= ServiceCost.DELIVERY_COST;
        }
        UserDAO userDAO = new UserDAO();
        User user=new User();
        try {
            user = userDAO.findEntityById(userId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return user.getAmount() >= orderAmount;
    }

    /**
     * Check if there is enough medicine quantity in store
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return true if there is enough medicine quantity in store, and false if isn't.
     */
    public static boolean checkAvailableQuantity(Map <String,String> parameters){
        long medicineId=0;
        int needQuantity=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.QUANTITY:
                    needQuantity=Integer.parseInt(parameter.getValue());
                    break;
                case ParameterOrder.MEDICINE_ID:
                    medicineId=Long.parseLong(parameter.getValue());
                    break;
            }
        }

        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        try {
            medicine = medicineDAO.findEntityById(medicineId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicine.getStoreQuantity()>=needQuantity;
    }

    /**
     * Check if user has recipe for specified medicine
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return true if user has recipe for specified medicine or if medicine can be sold without recipe,
     * false if user doesn't have recipe for specified medicine.
     */
    public static boolean checkRecipe (Map <String,String> parameters) {
        long medicineId=0;
        int needQuantity=0;
        long userId=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.QUANTITY:
                    needQuantity=Integer.parseInt(parameter.getValue());
                    break;
                case ParameterOrder.MEDICINE_ID:
                    medicineId=Long.parseLong(parameter.getValue());
                    break;
                case ParameterOrder.USER_ID:
                    userId=Long.parseLong(parameter.getValue());
                    break;
            }
        }

        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        try {
            medicine = medicineDAO.findEntityById(medicineId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (!medicine.isRecipe()){
            return true;
        }
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        try {
            recipe = recipeDAO.findRecipesByPatientMedicine(userId,medicineId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        int recipeQuantity=recipe.getMedicineQuantity();
        return (needQuantity<=recipeQuantity);
    }
}
