package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that contains DAO methods for working with "order" table from "pharmacy" schema
 */
public class OrderDAO extends AbstractDAO<Order>{

    private static final Logger LOG= LogManager.getLogger();
    private static final String SQL_SELECT_ALL_ORDERS = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine WHERE idorder=?";
    private static final String SQL_SELECT_ORDER_BY_USER = "SELECT O.idorder,O.fkBuyer,O.fkMedicine,M.medicineName,M.dosage, O.quantity,O.totalAmount,O.delivery FROM pharmacy.order O INNER JOIN pharmacy.medicine M ON M.idmedicine=O.fkMedicine WHERE fkBuyer=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM pharmacy.order WHERE idorder = ?;";
    private static final String SQL_CREATE_ORDER = "INSERT INTO pharmacy.order(fkBuyer,fkMedicine,quantity,totalAmount,delivery) values(?,?,?,?,?);";
    private static final String SQL_UPDATE_ORDER_BY_ENTITY="UPDATE pharmacy.order SET idorder=?,fkBuyer=?,fkMedicine=?,quantity=?,totalAmount=?,delivery=? WHERE idorder=?;";
    private static final String SQL_UPDATE_MEDICINE_BY_ENTITY="UPDATE pharmacy.medicine SET storeQuantity=? WHERE medicine.idmedicine=?;";
    private static final String SQL_UPDATE_USER_BY_ENTITY="UPDATE user SET amount=? WHERE iduser=?;";
    private static final String SQL_UPDATE_RECIPE_BY_ENTITY="UPDATE pharmacy.recipe SET medicineQuantity=?,expired=? WHERE idrecipe=?;";


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
     * @param userId - User ID whose orders need to be found.
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
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return orders;
    }


    /**
     * Method establish connection with database for deleting order with specified id from "order" table.
     * @param orderId number that define Order entity id that should be deleted.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean delete(long orderId) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_ORDER_BY_ID)){
            st.setLong(1,orderId);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Order' failed):", e);
        }
        return isDeleted;
    }

    /**
     * Method establish connection with database to add new order to "order" table.
     * @param entity - Order to be added to database.
     * @return true if operation of adding was executed, false - if wasn't.
     * @throws DAOException
     */
    public boolean create(Order entity, Recipe recipe) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        ProxyConnection cn=connectionPool.takeConnection();
        boolean isCreated;
        try (PreparedStatement st1=cn.prepareStatement(SQL_CREATE_ORDER);
                PreparedStatement st2=cn.prepareStatement(SQL_UPDATE_MEDICINE_BY_ENTITY);
                    PreparedStatement st3=cn.prepareStatement(SQL_UPDATE_USER_BY_ENTITY);
                        PreparedStatement st4=cn.prepareStatement(SQL_UPDATE_RECIPE_BY_ENTITY)){
            cn.setAutoCommit(false);
            st1.setLong(1,entity.getBuyer().getId());
            st1.setLong(2,entity.getMedicine().getId());
            st1.setInt(3,entity.getQuantity());
            st1.setDouble(4,entity.getTotalAmount());
            st1.setBoolean(5,entity.isDelivery());
            isCreated=0<st1.executeUpdate();

            st2.setInt(1,entity.getMedicine().getStoreQuantity());
            st2.setLong(2,entity.getMedicine().getId());
            st2.executeUpdate();

            st3.setDouble(1,entity.getBuyer().getAmount());
            st3.setLong(2,entity.getBuyer().getId());
            st3.executeUpdate();

            if (recipe!=null){
                st4.setInt(1,recipe.getMedicineQuantity());
                st4.setBoolean(2,recipe.isExpired());
                st4.setLong(3,recipe.getId());
                st4.executeUpdate();
            }
            cn.commit();
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (SQLException e2){

                LOG.error(e2.getMessage());
            }
            throw new DAOException("Impossible to execute request(request to update order failed):", e);
        } finally {
            if (cn!=null){
                try {
                    cn.close();
                } catch (SQLException e){
                    LOG.error("Impossible to close connection:",e);
                }
            }
        }
        return isCreated;
    }


    @Override
    public boolean create(Order entity) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }


    /**
     * Method establish connection with database to update order entity in "order" table.
     * @param entity - Order to be updated.
     * @return true if operation of updating was executed, false - if wasn't.
     * @throws DAOException
     */
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
