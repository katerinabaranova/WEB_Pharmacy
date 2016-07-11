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
            User user = userDao.findEntityById(1);
            System.out.println(users);
            System.out.println(user);
        } catch (ExceptionDAO e){

        }
    }
}
