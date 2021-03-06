package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterMedicine;
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
 * Class service for command classes for making operations with Medicine entity
 */
public class MedicineService {

    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new medicine for adding to database
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of creating was executed
     */
    public static boolean newMedicineCreate(Map<String,String> parameters){
        Medicine medicine=new Medicine();
        MedicineDAO medicineDAO=new MedicineDAO();
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterMedicine.MEDICINE_NAME:
                    medicine.setMedicineName(parameter.getValue());
                    break;
                case ParameterMedicine.MEDICINE_DOSAGE:
                    medicine.setDosage(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.MEDICINE_PACKAGE:
                    medicine.setPackageType(parameter.getValue());
                    break;
                case ParameterMedicine.PACK_QUANTITY:
                    medicine.setPackageQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.IN_STORE_QUANTITY:
                    medicine.setStoreQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.PRICE:
                    medicine.setPrice(Double.parseDouble(parameter.getValue()));
                    break;
                case ParameterMedicine.RECIPE:
                    medicine.setRecipe(Boolean.parseBoolean(parameter.getValue()));
                    break;
            }
        }

        boolean isCreated=false;
        try {
            isCreated = medicineDAO.create(medicine);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isCreated;
    }


    /**
     * Method call DAO method to delete medicine defined by Id from database
     * @param medicineId receiving from JSP medicine Id
     * @return boolean value if operation of deleting was executed
     */
    public static boolean deleteMedicine(long medicineId){
        MedicineDAO medicineDAO=new MedicineDAO();
        boolean isDeleted=false;
        try{
            isDeleted=medicineDAO.delete(medicineId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isDeleted;
    }


    /**
     * Method call DAO method to find medicine defined by Id in database
     * @param medicineId receiving from JSP medicine Id
     * @return Medicine with necessary Id
     */
    public static Medicine findMedicine(long medicineId){
        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        try{
            medicine=medicineDAO.findEntityById(medicineId);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicine;
    }

    /**
     * Method call DAO method to check if specified medicine need recipe
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if specified Medicine need recipe
     */
    public static boolean necessaryRecipeMedicine(Map<String,String> parameters){
        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=new Medicine();
        String medicineName="";
        int dosage=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()) {
                case ParameterRecipe.MEDICINE_NAME:
                    medicineName = parameter.getValue();
                    break;
                case ParameterRecipe.MEDICINE_DOSAGE:
                    dosage = Integer.parseInt(parameter.getValue());
                    break;
            }
        }
        try {
            medicine=medicineDAO.findIDByNameDosage(medicineName,dosage);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicine.isRecipe();
    }


    /**
     * Method call DAO method to update medicine defined by Medicine entity
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of updating was executed
     */
    public static boolean updateMedicineService(Map<String,String> parameters){
        Medicine medicine=new Medicine();
        MedicineDAO medicineDAO=new MedicineDAO();
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()) {
                case ParameterMedicine.MEDICINE_ID:
                    medicine.setId(Long.parseLong(parameter.getValue()));
                    break;
                case ParameterMedicine.MEDICINE_NAME:
                    medicine.setMedicineName(parameter.getValue());
                    break;
                case ParameterMedicine.MEDICINE_DOSAGE:
                    medicine.setDosage(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.MEDICINE_PACKAGE:
                    medicine.setPackageType(parameter.getValue());
                    break;
                case ParameterMedicine.PACK_QUANTITY:
                    medicine.setPackageQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.IN_STORE_QUANTITY:
                    medicine.setStoreQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterMedicine.PRICE:
                    medicine.setPrice(Double.parseDouble(parameter.getValue()));
                    break;
                case ParameterMedicine.RECIPE:
                    medicine.setRecipe(Boolean.parseBoolean(parameter.getValue()));
                    break;
            }
        }
        boolean isUpdate=false;
        try{
            isUpdate=medicineDAO.update(medicine);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isUpdate;
    }

    /**
     * Search appropriate medicines, that have the  name that appropriate medicineName  parameter
     * @param medicineName String value of medicine name receiving from JSP search form
     * @return list of appropriate medicine
     */
    public static List<Medicine> searchService(String medicineName){
        MedicineDAO medicineDAO=new MedicineDAO();
        List<Medicine> medicines=new ArrayList<>();
        try {
            medicines = medicineDAO.findEntityByName(medicineName);
        }catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicines;
    }


    /**
     * Method call DAO method to fill list of all medicines that are in database
     * @return List of medicines
     */
    public static List<Medicine> findAllMedicineService(){
        MedicineDAO medicineDAO=new MedicineDAO();
        List<Medicine> medicines=new ArrayList<>();
        try{
            medicines=medicineDAO.findAll();
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicines;
    }
}
