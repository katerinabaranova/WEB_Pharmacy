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
        Recipe recipe=new Recipe();
        String surname=request.getParameter(ParameterRecipe.SURNAME);
        String name=request.getParameter(ParameterRecipe.NAME);
        String medicineName=request.getParameter(ParameterRecipe.MEDICINE_NAME);
        int dosage=Integer.parseInt(request.getParameter(ParameterRecipe.MEDICINE_DOSAGE));
        UserDAO userDAO=new UserDAO();
        MedicineDAO medicineDAO=new MedicineDAO();
        RecipeDAO recipeDAO=new RecipeDAO();
        try {
            long idUser = userDAO.findID(surname, name);
            recipe.setPatientID(idUser);
            long idMedicine =medicineDAO.findIDByNameDosage(medicineName,dosage);
            recipe.setMedicineID(idMedicine);
            recipe.setMedicineQuantity(Integer.parseInt(request.getParameter(ParameterRecipe.MEDICINE_QUANTITY)));
            long idDoctor=userDAO.findEntityByLogin(request.getSession().getAttribute(AttributeConstant.LOGGED_USER).toString()).getUserID();
            recipe.setDoctorID(idDoctor);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        System.out.println(recipe);
        boolean isCreated=false;
        try {
            isCreated = recipeDAO.create(recipe);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isCreated;
    }
}
