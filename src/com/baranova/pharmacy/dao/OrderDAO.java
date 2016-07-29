package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order>{

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT idorder,fkBuyer,fkMedicine,quantity,totalAmount,delivery,paid FROM pharmacy.order";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT idorder,fkBuyer,fkMedicine,quantity,totalAmount,delivery,paid FROM pharmacy.order WHERE idorder=?";
    private static final String SQL_SELECT_ORDER_BY_USER = "SELECT idorder,fkBuyer,fkMedicine,quantity,totalAmount,delivery,paid FROM pharmacy.order WHERE fkBuyer=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM pharmacy.order WHERE idorder = ?;";
    private static final String SQL_CREATE_ORDER = "INSERT INTO pharmacy.order(fkBuyer,fkMedicine,quantity,totalAmount,delivery,paid) values(?,?,?,?,?,?);";
    private static final String SQL_UPDATE_ORDER_BY_ENTITY="UPDATE pharmacy.order SET idorder=?,fkBuyer=?,fkMedicine=?,quantity=?,totalAmount=?,delivery=?,paid=? WHERE idorder=?;";

    @Override
    public List<Order> findAll() throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_ORDERS)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("idorder"));
                order.setFkUserID(resultSet.getLong("fkBuyer"));
                order.setFkMedicineID(resultSet.getLong("fkMedicine"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalAmount(resultSet.getInt("totalAmount"));
                order.setPaid(resultSet.getBoolean("paid"));
                order.setDelivery(resultSet.getBoolean("delivery"));
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return orders;
    }

    @Override
    public Order findEntityById (long id)  throws DAOException {
        Order order = new Order();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ORDER_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getLong("idorder"));
                order.setFkUserID(resultSet.getLong("fkBuyer"));
                order.setFkMedicineID(resultSet.getLong("fkMedicine"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalAmount(resultSet.getInt("totalAmount"));
                order.setDelivery(resultSet.getBoolean("delivery"));
                order.setPaid(resultSet.getBoolean("paid"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return order;
    }

    public List<Order> findOrdersByUser(Long userId) throws DAOException {
        List<Order> orders = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ORDER_BY_USER)){
            st.setLong(1,userId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("idorder"));
                System.out.println(order);
                order.setFkUserID(resultSet.getLong("fkBuyer"));
                order.setFkMedicineID(resultSet.getLong("fkMedicine"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalAmount(resultSet.getInt("totalAmount"));
                order.setDelivery(resultSet.getBoolean("delivery"));
                order.setPaid(resultSet.getBoolean("paid"));

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return orders;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_ORDER_BY_ID)){
            st.setLong(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(Order entity) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    @Override
    public boolean create(Order entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_ORDER)){
            st.setLong(1,entity.getFkUserID());
            st.setLong(2,entity.getFkMedicineID());
            st.setInt(3,entity.getQuantity());
            st.setInt(4,entity.getTotalAmount());
            st.setBoolean(5,entity.isDelivery());
            st.setBoolean(6,entity.isPaid());
            isCreated=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Order entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_ORDER_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setLong(2,entity.getFkUserID());
            st.setLong(3,entity.getFkMedicineID());
            st.setInt(4,entity.getQuantity());
            st.setInt(5,entity.getTotalAmount());
            st.setBoolean(6,entity.isDelivery());
            st.setBoolean(7,entity.isPaid());
            st.setLong(8,entity.getId());
            isUpdate=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isUpdate;
    }
}
