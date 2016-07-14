package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO extends AbstractDAO <Integer,Medicine> {

    private static final String SQL_SELECT_ALL_MEDICINE = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,receipt FROM medicine";
    private static final String SQL_SELECT_MEDICINE_BY_ID = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,receipt FROM medicine WHERE medicine.idmedicine=?";
    private static final String SQL_SELECT_MEDICINE_BY_NAME = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,receipt FROM medicine WHERE medicine.medicineName=?";
    private static final String SQL_DELETE_MEDICINE_BY_ID = "DELETE FROM medicine WHERE medicine.idmedicine = ?;";
    private static final String SQL_CREATE_MEDICINE = "INSERT INTO medicine(medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,receipt) values(?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_MEDICINE_BY_ENTITY="UPDATE medicine SET idmedicine=?,medicineName=?,dosage=?,medicinePackage=?,packQuantity=?,price=?,instoreQuantity=?,receipt=? WHERE idmedicine=?;";

    @Override
    public List<Medicine> findAll() throws ExceptionDAO{
        List<Medicine> medicines = new ArrayList<>();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_ALL_MEDICINE);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setReceipt(resultSet.getBoolean("receipt"));
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return medicines;
    }

    @Override
    public Medicine findEntityById (Integer id)  throws ExceptionDAO{
        Medicine medicine = new Medicine();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_MEDICINE_BY_ID);
            st.setInt(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setReceipt(resultSet.getBoolean("receipt"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return medicine;
    }

    public Medicine findEntityByName(String name) throws ExceptionDAO {
        Medicine medicine = new Medicine();
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_SELECT_MEDICINE_BY_NAME);
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setReceipt(resultSet.getBoolean("receipt"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return medicine;
    }

    @Override
    public boolean delete(Integer id) throws ExceptionDAO {
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_DELETE_MEDICINE_BY_ID);
            st.setInt(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(Medicine entity) throws ExceptionDAO {
        return false;
    }

    @Override
    public boolean create(Medicine entity) throws ExceptionDAO{
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_CREATE_MEDICINE);
            st.setString(1,entity.getMedicineName());
            st.setInt(2,entity.getDosage());
            st.setString(3,entity.getPackageType());
            st.setInt(4,entity.getPackageQuantity());
            st.setInt(5,entity.getPrice());
            st.setInt(6,entity.getStoreQuantity());
            st.setBoolean(7,entity.isReceipt());
            isCreated=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return isCreated;
    }

    @Override
    public boolean update(Medicine entity) throws ExceptionDAO {
        ProxyConnection cn = null;
        PreparedStatement st = null;
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate=false;
        try {
            cn = connectionPool.takeConnection();
            st = cn.prepareStatement(SQL_UPDATE_MEDICINE_BY_ENTITY);
            st.setLong(1,entity.getId());
            st.setString(2,entity.getMedicineName());
            st.setInt(3,entity.getDosage());
            st.setString(4,entity.getPackageType());
            st.setInt(5,entity.getPackageQuantity());
            st.setInt(6,entity.getPrice());
            st.setInt(7,entity.getStoreQuantity());
            st.setBoolean(8,entity.isReceipt());
            st.setLong(9,entity.getId());
            isUpdate=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        } finally {
            close(st);
        }
        return isUpdate;
    }
}
