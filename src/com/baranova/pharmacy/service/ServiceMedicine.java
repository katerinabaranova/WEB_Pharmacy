package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterMedicine;
import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Class-service for command classes for making operations with Medicine entity
 */
public class ServiceMedicine {
    private static final Logger LOG= LogManager.getLogger();

    public static boolean newMedicineCreate(HttpServletRequest request){
        Medicine medicine=new Medicine();
        MedicineDAO medicineDAO=new MedicineDAO();
        medicine.setMedicineName(request.getParameter(ParameterMedicine.MEDICINE_NAME));
        medicine.setDosage(Integer.parseInt(request.getParameter(ParameterMedicine.MEDICINE_DOSAGE)));
        medicine.setPackageType(request.getParameter(ParameterMedicine.MEDICINE_PACKAGE));
        medicine.setPackageQuantity(Integer.parseInt(request.getParameter(ParameterMedicine.PACK_QUANTITY)));
        medicine.setStoreQuantity(Integer.parseInt(request.getParameter(ParameterMedicine.INSTORE_QUANTITY)));
        medicine.setPrice(Integer.parseInt(request.getParameter(ParameterMedicine.PRICE)));
        byte needRecipe=Byte.parseByte(request.getParameter(ParameterMedicine.RECIPE));
        if (needRecipe==1){
            medicine.setRecipe(true);
        } else {
            medicine.setRecipe(false);
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
     * Method call DAO method to delete medicine defined by id
     * @param id receiving from JSP medicine id
     * @return boolean value if operation of deleting was executed
     */
    public static boolean deleteMedicine(long id){
        MedicineDAO medicineDAO=new MedicineDAO();
        boolean isDeleted=false;
        try{
            isDeleted=medicineDAO.delete(id);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return isDeleted;
    }
}
