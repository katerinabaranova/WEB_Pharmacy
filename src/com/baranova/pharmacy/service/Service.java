package com.baranova.pharmacy.service;


import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.dao.OrderDAO;
import com.baranova.pharmacy.dao.RecipeDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.util.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private static final Logger LOG= LogManager.getLogger();

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

    public static List<Order>  showOrdersService(String login){
        UserDAO userDAO=new UserDAO();
        List<Order> orders=new ArrayList<>();
        User user;
        try {
            user = userDAO.findEntityByLogin(login);
            OrderDAO orderDAO=new OrderDAO();
            orders=orderDAO.findOrdersByUser(user.getId());
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return orders;
    }

    public static List<Recipe> showRecipesService(String login){
        UserDAO userDAO=new UserDAO();
        List<Recipe> recipes=new ArrayList<>();
        User user;
        try {
            user = userDAO.findEntityByLogin(login);
            RecipeDAO recipeDAO=new RecipeDAO();
            recipes=recipeDAO.findRecipesByPacient(user.getId());
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return recipes;
    }

    public static Medicine getMedicineService(Long id){
        MedicineDAO medicineDAO=new MedicineDAO();
        Medicine medicine=null;
        try {
            medicine = medicineDAO.findEntityById(id);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        return medicine;
    }

    public static List<Medicine> getAllMedicineService(){
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
