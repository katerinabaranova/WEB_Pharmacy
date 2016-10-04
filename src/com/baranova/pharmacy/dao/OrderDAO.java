package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that contains DAO methods for working with "order" table from "pharmacy" schema
 */
public class OrderDAO extends AbstractDAO<Order>{

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine WHERE idorder=?";
    private static final String SQL_SELECT_ORDER_BY_USER = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine WHERE fkBuyer=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM pharmacy.order WHERE idorder = ?;";
    private static final String SQL_CREATE_ORDER = "INSERT INTO pharmacy.order(fkBuyer,fkMedicine,quantity,totalAmount,delivery) values(?,?,?,?,?);";
    private static final String SQL_UPDATE_ORDER_BY_ENTITY="UPDATE pharmacy.order SET idorder=?,fkBuyer=?,fkMedicine=?,quantity=?,totalAmount=?,delivery=? WHERE idorder=?;";


    /**
     * Method establish connection with database for selecting all orders from "order" table.
     * @return List <Order> containing all of the elements from the table.
     * @throws DAOException
     */
    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_ORDERS)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("idorder"));
                long buyerID=resultSet.getLong("fkBuyer");
                User user=new User();
                user.setId(buyerID);
                order.setBuyer(user);
                long medicineID=resultSet.getLong("fkMedicine");
                Medicine medicine=new Medicine();
                medicine.setId(medicineID);
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                order.setMedicine(medicine);
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setDelivery(resultSet.getBoolean("delivery"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return orders;
    }

    /**
     * Method establish connection with database for selecting order with specified id from "order" table.
     * @param  orderId- specified order id that has to be found.
     * @return Order Object with specified id.
     * @throws DAOException
     */
    @Override
    public Order findEntityById (long orderId)  throws DAOException {
        Order order = new Order();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ORDER_BY_ID)){
            st.setLong(1,orderId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            order.setId(resultSet.getLong("idorder"));
            long buyerID=resultSet.getLong("fkBuyer");
            User user=new User();
            user.setId(buyerID);
            order.setBuyer(user);
            long medicineID=resultSet.getLong("fkMedicine");
            Medicine medicine=new Medicine();
            medicine.setId(medicineID);
            medicine.setMedicineName(resultSet.getString("medicineName"));
            medicine.setDosage(resultSet.getInt("dosage"));
            order.setMedicine(medicine);
            order.setQuantity(resultSet.getInt("quantity"));
            order.setTotalAmount(resultSet.getDouble("totalAmount"));
            order.setDelivery(resultSet.getBoolean("delivery"));
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return order;
    }


    /**
     * Method establish connection with database for selecting orders of specified user from "order" table.
     * @param userId - id of specified user, orders of whom has to be found.
     * @return List <Order> containing all orders of specified user.
     * @throws DAOException
     */
    public List<Order> findOrdersByUser(Long userId) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ORDER_BY_USER)){
            st.setLong(1,userId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("idorder"));
                long buyerID=resultSet.getLong("fkBuyer");
                User user=new User();
                user.setId(buyerID);
                order.setBuyer(user);
                long medicineID=resultSet.getLong("fkMedicine");
                Medicine medicine=new Medicine();
                medicine.setId(medicineID);
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                order.setMedicine(medicine);
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalAmount(resultSet.getDouble("totalAmount"));
                order.setDelivery(resultSet.getBoolean("delivery"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return orders;
    }


    @Override
    public boolean delete(long id) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_ORDER_BY_ID)){
            st.setLong(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return isDeleted;
    }

    @Override
    public boolean create(Order entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_ORDER)){
            st.setLong(1,entity.getBuyer().getId());
            st.setLong(2,entity.getMedicine().getId());
            st.setInt(3,entity.getQuantity());
            st.setDouble(4,entity.getTotalAmount());
            st.setBoolean(5,entity.isDelivery());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Order entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_ORDER_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setLong(2,entity.getBuyer().getId());
            st.setLong(3,entity.getMedicine().getId());
            st.setInt(4,entity.getQuantity());
            st.setDouble(5,entity.getTotalAmount());
            st.setBoolean(6,entity.isDelivery());
            st.setLong(7,entity.getId());
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return isUpdate;
    }
}
