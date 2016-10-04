package com.baranova.pharmacy.runner;

import com.baranova.pharmacy.dao.*;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger LOG= LogManager.getLogger();

    public static void main(String[] args) {

        OrderDAO medicineDAO=new OrderDAO();
        boolean isDeleted=false;
        try {
            List<Order> order=medicineDAO.findOrdersByUser(4L);            System.out.println(order);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
    }
}
