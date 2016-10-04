package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.*;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {

        MedicineDAO medicineDAO=new MedicineDAO();
        boolean isDeleted=false;
        try{
            Medicine medicine=medicineDAO.findEntityById(1);
            medicine.setStoreQuantity(3);
            isDeleted=medicineDAO.update(medicine);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
