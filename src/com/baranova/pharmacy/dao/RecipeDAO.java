package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Medicine;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import com.baranova.pharmacy.service.RecipeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains DAO methods for working with "recipe" table from "pharmacy" schema
 */

public class RecipeDAO extends AbstractDAO<Recipe> {

    private static final String SQL_SELECT_ALL_RECIPES = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe";
    private static final String SQL_SELECT_RECIPE_BY_ID = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe WHERE idrecipe=?";
    private static final String SQL_SELECT_RECIPE_BY_PATIENT_MEDICINE = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe WHERE fkPatient=? AND fkMedicine=? AND expired=?";
    private static final String SQL_SELECT_RECIPE_BY_PATIENT = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe WHERE fkPatient=?";
    private static final String SQL_SELECT_RECIPE_BY_DOCTOR = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe WHERE fkDoctor=?";
    private static final String SQL_DELETE_RECIPE_BY_ID = "DELETE FROM pharmacy.recipe WHERE idrecipe = ?;";
    private static final String SQL_CREATE_RECIPE = "INSERT INTO pharmacy.recipe(date, fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest) values(?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_RECIPE_BY_ENTITY="UPDATE pharmacy.recipe SET idrecipe=?,date=?,fkDoctor=?,fkPatient=?,fkMedicine=?,medicineQuantity=?,expired=?,renewRequest=? WHERE idrecipe=?;";
    private static final String SQL_SELECT_RECIPE_REQUEST_BY_DOCTOR = "SELECT idrecipe,date,fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest FROM pharmacy.recipe WHERE fkDoctor=? AND renewRequest=?";

    @Override
    public List<Recipe> findAll() throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_RECIPES)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDate(resultSet.getDate("date"));
                Long patientID=resultSet.getLong("fkPatient");
                Long doctorID=resultSet.getLong("fkDoctor");
                UserDAO userDAO=new UserDAO();
                User patient=userDAO.findEntityById(patientID);
                User doctor=userDAO.findEntityById(doctorID);
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Long medicineID=resultSet.getLong("fkMedicine");
                MedicineDAO medicineDAO=new MedicineDAO();
                Medicine medicine=medicineDAO.findEntityById(medicineID);
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }


    @Override
    public Recipe findEntityById (long id)  throws DAOException {
        Recipe recipe = new Recipe();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDate(resultSet.getDate("date"));
                Long patientID=resultSet.getLong("fkPatient");
                Long doctorID=resultSet.getLong("fkDoctor");
                UserDAO userDAO=new UserDAO();
                User patient=userDAO.findEntityById(patientID);
                User doctor=userDAO.findEntityById(doctorID);
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Long medicineID=resultSet.getLong("fkMedicine");
                MedicineDAO medicineDAO=new MedicineDAO();
                Medicine medicine=medicineDAO.findEntityById(medicineID);
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipe;
    }

    public List<Recipe> findRecipesByPatient(long patientId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PATIENT)){
            st.setLong(1,patientId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDate(resultSet.getDate("date"));
                Long patientID=resultSet.getLong("fkPatient");
                Long doctorID=resultSet.getLong("fkDoctor");
                UserDAO userDAO=new UserDAO();
                User patient=userDAO.findEntityById(patientID);
                User doctor=userDAO.findEntityById(doctorID);
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Long medicineID=resultSet.getLong("fkMedicine");
                MedicineDAO medicineDAO=new MedicineDAO();
                Medicine medicine=medicineDAO.findEntityById(medicineID);
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    public Recipe findRecipesByPatientMedicine(long patientId,long medicineId) throws DAOException {
        Recipe recipe = new Recipe();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PATIENT_MEDICINE)){
            st.setLong(1,patientId);
            st.setLong(2,medicineId);
            st.setBoolean(3,false);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            recipe.setId(resultSet.getLong("idrecipe"));
            recipe.setDate(resultSet.getDate("date"));
            User patient=new User();
            User doctor=new User();
            patient.setId(resultSet.getLong("fkPatient"));
            doctor.setId(resultSet.getLong("fkDoctor"));
            recipe.setPatient(patient);
            recipe.setDoctor(doctor);
            Medicine medicine=new Medicine();
            medicine.setId(resultSet.getLong("fkMedicine"));
            recipe.setMedicine(medicine);
            recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
            recipe.setExpired(resultSet.getBoolean("expired"));
            recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipe;
    }

    public List<Recipe> findRecipesByDoctor(long doctorId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_DOCTOR)){
            st.setLong(1,doctorId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDate(resultSet.getDate("date"));
                Long patientID=resultSet.getLong("fkPatient");
                Long doctorID=resultSet.getLong("fkDoctor");
                UserDAO userDAO=new UserDAO();
                User patient=userDAO.findEntityById(patientID);
                User doctor=userDAO.findEntityById(doctorID);
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Long medicineID=resultSet.getLong("fkMedicine");
                MedicineDAO medicineDAO=new MedicineDAO();
                Medicine medicine=medicineDAO.findEntityById(medicineID);
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    public List<Recipe> findRecipesRequest(long doctorId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_REQUEST_BY_DOCTOR)){
            st.setLong(1,doctorId);
            st.setLong(2,1);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDate(resultSet.getDate("date"));
                User patient=new User();
                patient.setId(resultSet.getLong("fkPatient"));
                User doctor=new User();
                doctor.setId(resultSet.getLong("fkDoctor"));
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Medicine medicine=new Medicine();
                medicine.setId(resultSet.getLong("fkMedicine"));
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipe.setRenewRequest(resultSet.getBoolean("renewRequest"));
                RecipeService.fillRecipe(recipe);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    @Override
    public boolean delete(long idRecipe) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_RECIPE_BY_ID)){
            st.setLong(1,idRecipe);
            isDeleted=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isDeleted;
    }

    @Override
    public boolean create(Recipe entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_RECIPE)){
            st.setDate(1,new java.sql.Date(entity.getDate().getTime()));
            st.setLong(2,entity.getDoctor().getId());
            st.setLong(3,entity.getPatient().getId());
            st.setLong(4,entity.getMedicine().getId());
            st.setInt(5,entity.getMedicineQuantity());
            st.setBoolean(6,entity.isExpired());
            st.setBoolean(7,entity.isRenewRequest());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Recipe entity) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_RECIPE_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setDate(2,new java.sql.Date(entity.getDate().getTime()));
            st.setLong(3,entity.getDoctor().getId());
            st.setLong(4,entity.getPatient().getId());
            st.setLong(5,entity.getMedicine().getId());
            st.setInt(6,entity.getMedicineQuantity());
            st.setBoolean(7,entity.isExpired());
            st.setBoolean(8,entity.isRenewRequest());
            st.setLong(9,entity.getId());
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isUpdate;
    }
}
