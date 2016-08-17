package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterRecipe;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class-service for command classes for making operations with Recipe entity.
 */
public class ServiceRecipe {
    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new recipe
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of creating was executed
     */
    public static boolean createNewRecipe(Map<String,String> parameters){

        UserDAO userDAO=new UserDAO();
        MedicineDAO medicineDAO=new MedicineDAO();
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        String name="";
        String surname="";
        String medicineName="";
        long doctorID=0;
        int dosage=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()) {
                case ParameterRecipe.NAME:
                    name = parameter.getValue();
                    break;
                case ParameterRecipe.SURNAME:
                    surname = parameter.getValue();
                    break;
                case ParameterRecipe.MEDICINE_NAME:
                    medicineName = parameter.getValue();
                    break;
                case ParameterRecipe.MEDICINE_DOSAGE:
                    dosage = Integer.parseInt(parameter.getValue());
                    break;
                case ParameterRecipe.MEDICINE_QUANTITY:
                    recipe.setMedicineQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterRecipe.DOCTOR_ID:
                    doctorID = Long.parseLong(parameter.getValue());
                    break;
            }
        }
        Date date=new Date();
        recipe.setDate(date);
        boolean isCreated=false;
        try {
            User patient=userDAO.findEntityNameSurname(surname,name);
            User doctor=userDAO.findEntityById(doctorID);
            recipe.setPatient(patient);
            recipe.setDoctor(doctor);
            Medicine medicine =medicineDAO.findIDByNameDosage(medicineName,dosage);
            recipe.setMedicine(medicine);
            isCreated=recipeDAO.create(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isCreated;
    }

    /**
     * Method call DAO method to find all doctor recipes
     * @param doctorID id of logged doctor
     * @return List of buyer recipes
     */
    public static List<Recipe> findDoctorRecipe(Long doctorID){
        List<Recipe> recipes=new ArrayList<>();
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesByDoctor(doctorID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

    /**
     * Method call DAO method to find all doctor recipes
     * @param buyerID id of logged doctor
     * @return List of doctor recipes
     */
    public static List<Recipe> findBuyerRecipe(Long buyerID){
        List<Recipe> recipes=new ArrayList<>();
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesByPatient(buyerID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

}
