package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.OrderDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args){

        try {
            OrderDAO orderDAO=new OrderDAO();
            User user=new User();
            user.setId(4);
            Order order=orderDAO.findEntityById(1);
            System.out.println(order);
            List<Order> orders=orderDAO.findAll();
            System.out.println(orders);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
