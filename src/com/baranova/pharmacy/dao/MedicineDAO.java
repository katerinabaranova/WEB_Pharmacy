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

/**
 * Class that contains DAO methods for working with "medicine" table from "pharmacy" schema
 */
public class MedicineDAO extends AbstractDAO <Medicine> {

    private static final String SQL_SELECT_ALL_MEDICINE = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,storeQuantity,recipe FROM pharmacy.medicine";
    private static final String SQL_SELECT_MEDICINE_BY_ID = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,storeQuantity,recipe FROM pharmacy.medicine WHERE medicine.idmedicine=?";
    private static final String SQL_SELECT_MEDICINE_BY_NAME = "SELECT idmedicine,medicineName,dosage,medicinePackage,packQuantity,price,storeQuantity,recipe FROM pharmacy.medicine WHERE medicine.medicineName=?";
    private static final String SQL_SELECT_MEDICINE_BY_NAME_DOSAGE = "SELECT idmedicine FROM medicine WHERE medicine.medicineName=? AND medicine.dosage=?";
    private static final String SQL_DELETE_MEDICINE_BY_ID = "DELETE FROM pharmacy.medicine WHERE medicine.idmedicine = ?;";
    private static final String SQL_CREATE_MEDICINE = "INSERT INTO medicine(medicineName,dosage,medicinePackage,packQuantity,price,storeQuantity,recipe) values(?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_MEDICINE_BY_ENTITY="UPDATE pharmacy.medicine SET idmedicine=?,medicineName=?,dosage=?,medicinePackage=?,packQuantity=?,price=?,storeQuantity=?,recipe=? WHERE medicine.idmedicine=?;";

    /**
     * Method establish connection with database for selecting all medicines from  "medicine" table.
     * @return List <Medicine> containing all of the elements from the table.
     * @throws DAOException
     */
    @Override
    public List<Medicine> findAll() throws DAOException {
        List<Medicine> medicines = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_MEDICINE)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getLong("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setStoreQuantity(resultSet.getInt("storeQuantity"));
                medicine.setPrice(resultSet.getDouble("price"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return medicines;
    }

    /**
     * Method establish connection with database for selecting medicine with specified id from "medicine" table.
     * @param medicineId - specified medicine id that has to be found.
     * @return Medicine Object with specified id.
     * @throws DAOException
     */
    @Override
    public Medicine findEntityById (long medicineId)  throws DAOException {
        Medicine medicine = new Medicine();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_BY_ID)){
            st.setLong(1,medicineId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                medicine.setId(resultSet.getLong("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setStoreQuantity(resultSet.getInt("storeQuantity"));
                medicine.setPrice(resultSet.getDouble("price"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return medicine;
    }

    /**
     * Method establish connection with database for selecting medicine with specified name and dosage from "medicine" table.
     * @param name - specified medicine name, that has to be found.
     * @param dosage - specified medicine dosage, that has to be found.
     * @return Medicine Object that appropriate name and dosage parameters.
     * @throws DAOException
     */
    public Medicine findIDByNameDosage(String name, int dosage) throws DAOException {
        Medicine medicine=new Medicine();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_BY_NAME_DOSAGE)){
            st.setString(1,name);
            st.setInt(2,dosage);
            ResultSet resultSet=st.executeQuery();
            while (resultSet.next()){
                medicine.setId(resultSet.getLong("idmedicine"));
            }
        } catch (SQLException e){
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return medicine;
    }

    /**
     * Method establish connection with database for selecting medicine with specified name from "medicine" table.
     * @param name - specified medicine name, that has to be found.
     * @return List <Medicine> containing all of the elements from the table that appropriate name parameter.
     * @throws DAOException
     */
    public List<Medicine> findEntityByName(String name) throws DAOException {
        List<Medicine> medicines = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_MEDICINE_BY_NAME)){
            st.setString(1,name);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Medicine medicine = new Medicine();
                medicine.setId(resultSet.getLong("idmedicine"));
                medicine.setMedicineName(resultSet.getString("medicineName"));
                medicine.setDosage(resultSet.getInt("dosage"));
                medicine.setPackageType(resultSet.getString("medicinePackage"));
                medicine.setPackageQuantity(resultSet.getInt("packQuantity"));
                medicine.setPrice(resultSet.getDouble("price"));
                medicine.setStoreQuantity(resultSet.getInt("storeQuantity"));
                medicine.setRecipe(resultSet.getBoolean("recipe"));
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return medicines;
    }


    /**
     * Method establish connection with database for deleting medicine with specified id from "medicine" table.
     * @param medicineId number that define Medicine entity id that should be deleted.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean delete(long medicineId) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_MEDICINE_BY_ID)){
            st.setLong(1,medicineId);
            isDeleted=0<st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return isDeleted;
    }

    /**
     * Method establish connection with database to add new medicine to "medicine" table.
     * @param entity - Medicine to be added to database.
     * @return true if operation of adding was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean create(Medicine entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_MEDICINE)){
            st.setString(1,entity.getMedicineName());
            st.setInt(2,entity.getDosage());
            st.setString(3,entity.getPackageType());
            st.setInt(4,entity.getPackageQuantity());
            st.setDouble(5,entity.getPrice());
            st.setInt(6,entity.getStoreQuantity());
            st.setBoolean(7,entity.isRecipe());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return isCreated;
    }

    /**
     * Method establish connection with database to update medicine entity in "medicine" table.
     * @param entity - Medicine to be updated.
     * @return true if operation of updating was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean update(Medicine entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_MEDICINE_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setString(2,entity.getMedicineName());
            st.setInt(3,entity.getDosage());
            st.setString(4,entity.getPackageType());
            st.setInt(5,entity.getPackageQuantity());
            st.setDouble(6,entity.getPrice());
            st.setInt(7,entity.getStoreQuantity());
            st.setBoolean(8,entity.isRecipe());
            st.setLong(9,entity.getId());
            isUpdate=0<st.executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Impossible to execute request(request to table 'Medicine' failed):", e);
        }
        return isUpdate;
    }
}
