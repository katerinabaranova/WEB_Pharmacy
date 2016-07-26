package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        try {
            UserDAO userDAO = new UserDAO();
            //List<Medicine> users = userDAO.findEntityByLogin("ivnachik");
            User user=userDAO.findEntityByLogin("ivnachik");
            System.out.println(user.getPassword());
            //Medicine user3=new Medicine("Aygmentin",457,"battle/suspension",5,13,2,true);
            //Medicine user3=med.findEntityById(4);
            //user3.setStoreQuantity(7);
            //med.update(user3);
            //User user4=new User(3,"klepkin","11ght2","Andrew","Kovalev","Zhlobin","serdicha",35,"klepkin@me.com","+375334567456",2);
            //userDao.update(user3);
            //med.delete(3);
        } catch (DAOException e){
            LOG.error("smth");
        }
    }
}
