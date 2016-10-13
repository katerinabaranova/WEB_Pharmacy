package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterRecipe;
import com.baranova.pharmacy.constant.RenewRecipeConstant;
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
 * Class service for command classes for making operations with Recipe entity
 */
public class RecipeService {
    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new recipe
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of creating was executed
     */
    public static Recipe createNewRecipe(Map<String,String> parameters){

        UserDAO userDAO=new UserDAO();
        MedicineDAO medicineDAO=new MedicineDAO();
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        String name="";
        String surname="";
        String medicineName="";
        long doctorId=0;
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
                    doctorId = Long.parseLong(parameter.getValue());
                    break;
            }
        }
        Date date=new Date();
        recipe.setDate(date);
        boolean isCreated=false;
        try {
            User patient=userDAO.findEntityNameSurname(surname,name);
            User doctor=userDAO.findEntityById(doctorId);
            recipe.setPatient(patient);
            recipe.setDoctor(doctor);
            Medicine medicine =medicineDAO.findIDByNameDosage(medicineName,dosage);
            recipe.setMedicine(medicine);
            isCreated=recipeDAO.create(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isCreated?recipe:null;
    }


    /**
     * Method call DAO method to find all doctor recipes
     * @param doctorId id of logged doctor
     * @return List of doctor recipes
     */
    public static List<Recipe> findDoctorRecipe(long doctorId){
        List<Recipe> recipes=new ArrayList<>();
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesByDoctor(doctorId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

    /**
     * Method call DAO method to find all buyer recipes
     * @param buyerId id of logged buyer
     * @return List of buyer recipes
     */
    public static List<Recipe> findBuyerRecipe(long buyerId){
        List<Recipe> recipes=new ArrayList<>();
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesByPatient(buyerId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

    /**
     * Method call DAO method to check if recipe is expired
     * @param recipeId recipe of id to be checked
     * @return boolean value if recipe is expired
     */
    public static boolean checkIsExpired(long recipeId){
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        try {
            recipe = recipeDAO.findEntityById(recipeId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipe.isExpired();
    }

    /**
     * Method call DAO method to update recipe value on renew request field
     * @param recipeId recipe of id to be update
     * @return boolean value if recipe is expired
     */
    public static boolean renewRequest(long recipeId){
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        try {
            recipe = recipeDAO.findEntityById(recipeId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        recipe.setRenewRequest(true);
        boolean isUpdated=false;
        try {
            isUpdated = recipeDAO.update(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isUpdated;
    }


    /**
     * Method call DAO method to find all doctor renew recipe requests
     * @param doctorId id of logged doctor
     * @return List of doctor recipes
     */
    public static List<Recipe> findDoctorRecipeRequests(long doctorId){
        List<Recipe> recipes=new ArrayList<>();
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesRequest(doctorId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

    /**
     * Method call DAO method to create new recipe based on the old recipe
     * @param recipeId - id of recipe to copied
     * @return new recipe created based on old recipe
     */
    public static Recipe renewRecipe(long recipeId){
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe;
        Recipe newRecipe=new Recipe();
        boolean isCreated=false;
        try {
            recipe=recipeDAO.findEntityById(recipeId);
            recipe.setRenewRequest(false);
            recipeDAO.update(recipe);
            newRecipe.setMedicine(recipe.getMedicine());
            newRecipe.setDoctor(recipe.getDoctor());
            newRecipe.setPatient(recipe.getPatient());
            newRecipe.setRenewRequest(false);
            newRecipe.setMedicineQuantity(RenewRecipeConstant.RENEW_RECIPE_QUANTITY);
            newRecipe.setExpired(false);
            isCreated=recipeDAO.create(newRecipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isCreated?newRecipe:null;
    }


    /**
     * Method prepare recipe for  modifying according to the new order
     * @param userId id of patient whose recipe is
     * @param medicineId id of recipe's medicine
     * @param quantity - quantity of medicine in order
     * @return recipe that updated according to the new order
     */
    public static Recipe OrderRecipeUpdate(long userId, long medicineId, int quantity){
        RecipeDAO recipeDAO=new RecipeDAO();
        Recipe recipe=new Recipe();
        try {
            recipe=recipeDAO.findRecipesByPatientMedicine(userId,medicineId);
            recipe.setMedicineQuantity(recipe.getMedicineQuantity()-quantity);
            if (recipe.getMedicineQuantity()==0){
                recipe.setExpired(true);
            }
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipe;

    }
}
