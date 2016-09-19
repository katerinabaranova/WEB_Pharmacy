package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.*;
import com.baranova.pharmacy.entity.*;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.service.Service;
import com.baranova.pharmacy.service.ServiceRecipe;
import com.baranova.pharmacy.util.PatternCheck;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {

        MedicineDAO medicineDAO=new MedicineDAO();
        boolean isDeleted=false;
        try{
            isDeleted=medicineDAO.delete(9);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
