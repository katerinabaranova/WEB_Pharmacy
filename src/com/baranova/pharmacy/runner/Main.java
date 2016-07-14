package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        try {
            MedicineDAO med = new MedicineDAO();
            /*List<Medicine> users = med.findAll();
            System.out.println(users);
            Medicine user1=med.findEntityById(1);
            System.out.println(user1);*/
            //Medicine user3=new Medicine("Aygmentin",457,"battle/suspension",5,13,2,true);
            Medicine user3=med.findEntityById(4);
            user3.setStoreQuantity(7);
            med.update(user3);
            //User user4=new User(3,"klepkin","11ght2","Andrew","Kovalev","Zhlobin","serdicha",35,"klepkin@me.com","+375334567456",2);
            //userDao.update(user3);
            med.delete(3);
        } catch (ExceptionDAO e){
            LOG.error("smth");
        }
    }
}
