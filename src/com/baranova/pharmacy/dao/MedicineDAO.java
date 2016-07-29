package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO extends AbstractDAO <Medicine> {

    private static final String SQL_SELECT_ALL_MEDICINE = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,recipe FROM medicine";
    private static final String SQL_SELECT_MEDICINE_BY_ID = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,recipe FROM medicine WHERE medicine.idmedicine=?";
    private static final String SQL_SELECT_MEDICINE_BY_NAME = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,recipe FROM medicine WHERE medicine.medicineName=?";
    private static final String SQL_DELETE_MEDICINE_BY_ID = "DELETE FROM medicine WHERE medicine.idmedicine = ?;";
    private static final String SQL_CREATE_MEDICINE = "INSERT INTO medicine(medicineName,dosage,medicinePackage,packQuantity,price,instoreQuantity,recipe) values(?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_MEDICINE_BY_ENTITY="UPDATE medicine SET idmedicine=?,medicineName=?,dosage=?,medicinePackage=?,packQuantity=?,price=?,instoreQuantity=?,recipe=? WHERE idmedicine=?;";
    private static final String SQL_SELECT_MEDICINE_NAME_BY_ID="SELECT medicineName FROM pharmacy.medicine WHERE idmedicine=?;";

    @Override
    public List<Medicine> findAll() throws DAOException {
        List<Medicine> medicines = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_MEDICINE)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return medicines;
    }

    @Override
    public Medicine findEntityById (long id)  throws DAOException {
        Medicine medicine = new Medicine();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return medicine;
    }


    public Medicine findMedicineNameById (long id)  throws DAOException {
        Medicine medicine = new Medicine();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_NAME_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                medicine.setMedicineName(resultSet.getString("medicineName"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return medicine;
    }

    public List<Medicine> findEntityByName(String name) throws DAOException {
        List<Medicine> medicines = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_BY_NAME)){
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getInt("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getInt("price"));
                medicine.setStoreQuantity(resultSet.getInt("instoreQuantity"));
                byte isRecipe=resultSet.getByte("recipe");
                if (isRecipe==0) {medicine.setRecipe(false);}
                    else {medicine.setRecipe(true);}
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return medicines;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_MEDICINE_BY_ID)){
            st.setLong(1,id);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(Medicine entity) throws DAOException {
        return false;
    }

    @Override
    public boolean create(Medicine entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_MEDICINE)){
            st.setString(1,entity.getMedicineName());
            st.setInt(2,entity.getDosage());
            st.setString(3,entity.getPackageType());
            st.setInt(4,entity.getPackageQuantity());
            st.setInt(5,entity.getPrice());
            st.setInt(6,entity.getStoreQuantity());
            st.setBoolean(7,entity.isRecipe());
            isCreated=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Medicine entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_MEDICINE_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setString(2,entity.getMedicineName());
            st.setInt(3,entity.getDosage());
            st.setString(4,entity.getPackageType());
            st.setInt(5,entity.getPackageQuantity());
            st.setInt(6,entity.getPrice());
            st.setInt(7,entity.getStoreQuantity());
            st.setBoolean(8,entity.isRecipe());
            st.setLong(9,entity.getId());
            isUpdate=st.execute();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table failed):", e);
        }
        return isUpdate;
    }
}
