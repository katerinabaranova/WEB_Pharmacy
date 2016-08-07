package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.AttributeConstant;
import com.baranova.pharmacy.constant.ParameterRecipe;
import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Class-service for command classes for making operations with Recipe entity.
 */
public class ServiceRecipe {
    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new recipe
     * @param request HttpServletRequest
     * @return boolean value if operation of creating was executed
     */
    public static boolean createNewRecipe(HttpServletRequest request){
        String surname=request.getParameter(ParameterRecipe.SURNAME);
        String name=request.getParameter(ParameterRecipe.NAME);
        String medicineName=request.getParameter(ParameterRecipe.MEDICINE_NAME);
        int dosage=Integer.parseInt(request.getParameter(ParameterRecipe.MEDICINE_DOSAGE));
        UserDAO userDAO=new UserDAO();
        MedicineDAO medicineDAO=new MedicineDAO();
        RecipeDAO recipeDAO=new RecipeDAO();
        boolean isCreated=false;
        try {
            Recipe recipe=new Recipe();
            long idUser = userDAO.findID(surname, name);
            recipe.setPatientID(idUser);
            long idMedicine =medicineDAO.findIDByNameDosage(medicineName,dosage);
            recipe.setMedicineID(idMedicine);
            recipe.setMedicineQuantity(Integer.parseInt(request.getParameter(ParameterRecipe.MEDICINE_QUANTITY)));
            long idDoctor=userDAO.findEntityByLogin(request.getSession().getAttribute(AttributeConstant.LOGGED_USER).toString()).getUserID();
            recipe.setDoctorID(idDoctor);
            isCreated=recipeDAO.create(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }

        return isCreated;
    }

    /**
     * Method call DAO method to find all doctor recipes
     * @param request HttpServletRequest
     * @return List of doctor recipes
     */
    public static List<Recipe> findDoctorRecipe(HttpServletRequest request){
        List<Recipe> recipes=new ArrayList<>();
        Long doctorID=Long.parseLong(request.getSession().getAttribute(AttributeConstant.LOGGED_ID).toString());
        try {
            RecipeDAO recipeDAO = new RecipeDAO();
            recipes = recipeDAO.findRecipesByDoctor(doctorID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }
}
