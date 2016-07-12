package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.ExceptionDAO;

import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            UserDAO userDao = new UserDAO();
            List<User> users = userDao.findAll();
            List<User> user = userDao.findEntityByRole(2);
            User user1=userDao.findEntityById(1);
            System.out.println(users);
            System.out.println("role="+user);
            System.out.println(user1);
            User user3=new User(3,"klepkin","11ght2","Andrew","Kovalev","Zhlobin","zhykova pr.",5,"klepkin@me.com","+375334567456",2);
            //userDao.create(user3);
        } catch (ExceptionDAO e){

        }
    }
}
