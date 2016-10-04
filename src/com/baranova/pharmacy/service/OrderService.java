package com.baranova.pharmacy.service;

import com.baranova.pharmacy.constant.ParameterOrder;
import com.baranova.pharmacy.constant.ServiceCost;
import com.baranova.pharmacy.dao.MedicineDAO;
import com.baranova.pharmacy.dao.OrderDAO;
import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class-service for command classes for making operations with Order entity.
 */
public class OrderService {
    private static final Logger LOG= LogManager.getLogger();

    /**
     * Method call DAO method to create new order
     * @param parameters Map<String,String> that contain name of parameters and theirs value
     * @return boolean value if operation of creating was executed
     */
    public static boolean newOrderCreate(Map<String,String> parameters){
        Order order=new Order();
        long userID=0;
        OrderDAO orderDAO=new OrderDAO();
        long medicineID=0;
        for (Map.Entry<String,String> parameter:parameters.entrySet()) {
            switch (parameter.getKey()){
                case ParameterOrder.MEDICINE_ID:
                    medicineID=Long.parseLong(parameter.getValue());
                    break;
                case ParameterOrder.QUANTITY:
                    order.setQuantity(Integer.parseInt(parameter.getValue()));
                    break;
                case ParameterOrder.DELIVERY:
                    order.setDelivery(Boolean.parseBoolean(parameter.getValue()));
                    break;
                case ParameterOrder.USER_ID:
                    userID=Long.parseLong(parameter.getValue());
                    break;
            }
        }
        boolean isCreated=false;
        MedicineDAO medicineDAO=new MedicineDAO();
        UserDAO userDAO=new UserDAO();
        User user;
        Medicine medicine;
        try {
            user=userDAO.findEntityById(userID);
            order.setBuyer(user);
            medicine=medicineDAO.findEntityById(medicineID);
            order.setMedicine(medicine);
            order.setTotalAmount(medicine.getPrice()*order.getQuantity()+(order.isDelivery()?ServiceCost.DELIVERY_COST:0));
            isCreated = orderDAO.create(order);
            int newStoreQuantity=medicine.getStoreQuantity()-order.getQuantity();
            medicine.setStoreQuantity(newStoreQuantity);
            medicineDAO.update(medicine);
            double newUserAmount=user.getAmount()-order.getTotalAmount();
            user.setAmount(newUserAmount);
            userDAO.update(user);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }

        return isCreated;
    }

    /**
     * Method call DAO method to fill List of user's orders
     * @param userID id of logged user
     * @return List<Order>
     */
    public static List<Order> showOrdersService(Long userID){
        OrderDAO orderDAO=new OrderDAO();
        List<Order> orders=new ArrayList<>();
        try {
            orders=orderDAO.findOrdersByUser(userID);
        } catch (DAOException e){
            LOG.error(e.getMessage());
        }
        for (Order order:orders){
            try {
                MedicineDAO medicineDAO=new MedicineDAO();
                Medicine checkMedicine=medicineDAO.findEntityById(order.getMedicine().getId());
                order.getMedicine().setMedicineName(checkMedicine.getMedicineName());
                order.getMedicine().setDosage(checkMedicine.getDosage());
            } catch (DAOException e){
                LOG.error(e.getMessage());
            }
        }
        return orders;
    }

}
