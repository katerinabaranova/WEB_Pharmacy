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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class ensure
 */
public class OrderParametersCheck {
    private static final Logger LOG= LogManager.getLogger();

    public static boolean checkPayAbility(Map<String,String> parameters) {
        long userID=0;
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
                    userID=Long.parseLong(parameter.getValue());
                    break;
            }
        }
        if (delivery) {
            orderAmount+= ServiceCost.DELIVERY_COST;
        }
        UserDAO userDAO = new UserDAO();
        User user=new User();
        try {
            user = userDAO.findEntityById(userID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        double availableBalance=user.getAmount();

        return availableBalance >= orderAmount;
    }

    public static boolean checkAvailableQuantity(Map <String,String> parameters){
        long medicineID=0;
        int needQuantity=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.QUANTITY:
                    needQuantity=Integer.parseInt(parameter.getValue());
                    break;
                case ParameterOrder.MEDICINE_ID:
                    medicineID=Long.parseLong(parameter.getValue());
                    break;
            }
        }

        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        try {
            medicine = medicineDAO.findEntityById(medicineID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicine.getStoreQuantity()>=needQuantity;
    }

    public static boolean checkRecipe (Map <String,String> parameters) {
        long medicineID=0;
        int needQuantity=0;
        long userID=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.QUANTITY:
                    needQuantity=Integer.parseInt(parameter.getValue());
                    break;
                case ParameterOrder.MEDICINE_ID:
                    medicineID=Long.parseLong(parameter.getValue());
                    break;
                case ParameterOrder.USER_ID:
                    userID=Long.parseLong(parameter.getValue());
                    break;
            }
        }

        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        try {
            medicine = medicineDAO.findEntityById(medicineID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        if (!medicine.isRecipe()){
            return true;
        }
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        try {
            recipe = recipeDAO.findRecipesByPatientMedicine(userID,medicineID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        int recipeQuantity=recipe.getMedicineQuantity();
        return (needQuantity<=recipeQuantity);
    }
}
