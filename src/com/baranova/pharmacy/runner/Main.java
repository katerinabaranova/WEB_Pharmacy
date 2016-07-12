package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){


        try {
            UserDAO userDao = new UserDAO();
            List<User> users = userDao.findAll();
            System.out.println(users);
            List<User> user = userDao.findEntityByRole(2);
            User user1=userDao.findEntityById(1);
            System.out.println("role="+user);
            System.out.println(user1);
            User user3=new User(3,"klepkin","11ght2","Andrew","Kovalev","Zhlobin","zhykova pr.",5,"klepkin@me.com","+375334567456",2);
            //userDao.create(user3);
            User user4=new User(3,"klepkin","11ght2","Andrew","Kovalev","Zhlobin","serdicha",35,"klepkin@me.com","+375334567456",2);
            userDao.update(user3);
            //userDao.delete(3);
        } catch (ExceptionDAO e){
            LOG.error("smth");
        }
    }
}
