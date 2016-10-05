package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.constant.database_constant.MedicineTable;
import com.baranova.pharmacy.constant.database_constant.RecipeTable;
import com.baranova.pharmacy.constant.database_constant.UserTable;
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
    private static final String SQL_SELECT_RECIPE_BY_PATIENT = "SELECT R.idrecipe,R.date,R.fkDoctor,U.surname,R.fkPatient,R.fkMedicine,M.medicineName,M.dosage,R.medicineQuantity,R.expired,R.renewRequest FROM pharmacy.recipe R INNER JOIN pharmacy.user U ON U.iduser=R.fkDoctor INNER JOIN pharmacy.medicine M ON M.idmedicine=R.fkMedicine WHERE fkPatient=?";
    private static final String SQL_SELECT_RECIPE_BY_DOCTOR = "SELECT R.idrecipe,R.date,R.fkDoctor,R.fkPatient,U.surname, U.name,R.fkMedicine,M.medicineName,M.dosage,R.medicineQuantity,R.expired,R.renewRequest FROM pharmacy.recipe R INNER JOIN pharmacy.user U ON U.iduser=R.fkPatient INNER JOIN pharmacy.medicine M ON M.idmedicine=R.fkMedicine WHERE fkDoctor=?";
    private static final String SQL_DELETE_RECIPE_BY_ID = "DELETE FROM pharmacy.recipe WHERE idrecipe = ?;";
    private static final String SQL_CREATE_RECIPE = "INSERT INTO pharmacy.recipe(date, fkDoctor,fkPatient,fkMedicine,medicineQuantity,expired,renewRequest) values(?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE_RECIPE_BY_ENTITY="UPDATE pharmacy.recipe SET idrecipe=?,date=?,fkDoctor=?,fkPatient=?,fkMedicine=?,medicineQuantity=?,expired=?,renewRequest=? WHERE idrecipe=?;";
    private static final String SQL_SELECT_RECIPE_REQUEST_BY_DOCTOR = "SELECT R.idrecipe,R.date,R.fkDoctor,R.fkPatient,U.surname, U.name,R.fkMedicine,M.medicineName,M.dosage,R.medicineQuantity,R.expired,R.renewRequest FROM pharmacy.recipe R INNER JOIN pharmacy.user U ON U.iduser=R.fkPatient INNER JOIN pharmacy.medicine M ON M.idmedicine=R.fkMedicine WHERE fkDoctor=? AND renewRequest=?";


    /**
     * Method establish connection with database for selecting all recipes from  "recipe" table.
     * @return List <Recipe> containing all of the elements from the table.
     * @throws DAOException
     */
    @Override
    public List<Recipe> findAll() throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_RECIPES)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
                recipe.setDate(resultSet.getDate(RecipeTable.DATE));
                User patient=new User();
                User doctor=new User();
                patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
                doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Medicine medicine=new Medicine();
                medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
                recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
                recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    /**
     * Method establish connection with database for selecting recipe with specified id from "recipe" table.
     * @param recipeId - specified recipe id that has to be found.
     * @return Recipe Object with specified id.
     * @throws DAOException
     */
    @Override
    public Recipe findEntityById (long recipeId)  throws DAOException {
        Recipe recipe = new Recipe();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_ID)){
            st.setLong(1,recipeId);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
            recipe.setDate(resultSet.getDate(RecipeTable.DATE));
            User patient=new User();
            User doctor=new User();
            patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
            doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
            recipe.setPatient(patient);
            recipe.setDoctor(doctor);
            Medicine medicine=new Medicine();
            medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
            recipe.setMedicine(medicine);
            recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
            recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
            recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipe;
    }

    /**
     * Method establish connection with database for selecting all recipes specified by user id from "recipe" table.
     * @param patientId - User id whose recipes need to be found.
     * @return List<Recipe> list of user recipes.
     * @throws DAOException
     */
    public List<Recipe> findRecipesByPatient(long patientId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PATIENT)){
            st.setLong(1,patientId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
                recipe.setDate(resultSet.getDate(RecipeTable.DATE));
                User patient=new User();
                patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
                User doctor=new User();
                doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
                doctor.setSurname(resultSet.getString(UserTable.SURNAME));
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Medicine medicine=new Medicine();
                medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
                medicine.setMedicineName(resultSet.getString(MedicineTable.MEDICINE_NAME));
                medicine.setDosage(resultSet.getInt(MedicineTable.DOSAGE));
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
                recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
                recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    /**
     * Method establish connection with database for selecting all recipes specified by user id and medicine id from "recipe" table.
     * @param patientId - User id whose recipe need to be found.
     * @param medicineId - medicine id which recipe need to be found.
     * @return Recipe that appropriate patient id and medicine id.
     * @throws DAOException
     */
    public Recipe findRecipesByPatientMedicine(long patientId,long medicineId) throws DAOException {
        Recipe recipe = new Recipe();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PATIENT_MEDICINE)){
            st.setLong(1,patientId);
            st.setLong(2,medicineId);
            st.setBoolean(3,false);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
            recipe.setDate(resultSet.getDate(RecipeTable.DATE));
            User patient=new User();
            User doctor=new User();
            patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
            doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
            recipe.setPatient(patient);
            recipe.setDoctor(doctor);
            Medicine medicine=new Medicine();
            medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
            recipe.setMedicine(medicine);
            recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
            recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
            recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipe;
    }


    /**
     * Method establish connection with database for selecting all recipes specified by user id from "recipe" table.
     * @param doctorId - doctor id whose recipes need to be found.
     * @return List<Recipe> list of doctor recipes.
     * @throws DAOException
     */
    public List<Recipe> findRecipesByDoctor(long doctorId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_DOCTOR)){
            st.setLong(1,doctorId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
                recipe.setDate(resultSet.getDate(RecipeTable.DATE));
                User patient=new User();
                patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
                patient.setSurname(resultSet.getString(UserTable.SURNAME));
                patient.setName(resultSet.getString(UserTable.NAME));
                User doctor=new User();
                doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Medicine medicine=new Medicine();
                medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
                medicine.setMedicineName(resultSet.getString(MedicineTable.MEDICINE_NAME));
                medicine.setDosage(resultSet.getInt(MedicineTable.DOSAGE));
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
                recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
                recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    /**
     * Method establish connection with database for selecting all recipes that have renew requests of specified doctor from "recipe" table.
     * @param doctorId - doctor id whose recipes need to be found.
     * @return List<Recipe> list of doctor recipes that have renew requests.
     * @throws DAOException
     */
    public List<Recipe> findRecipesRequest(long doctorId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_REQUEST_BY_DOCTOR)){
            st.setLong(1,doctorId);
            st.setBoolean(2,true);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong(RecipeTable.RECIPE_ID));
                recipe.setDate(resultSet.getDate(RecipeTable.DATE));
                User patient=new User();
                patient.setId(resultSet.getLong(RecipeTable.FK_PATIENT));
                patient.setSurname(resultSet.getString(UserTable.SURNAME));
                patient.setName(resultSet.getString(UserTable.NAME));
                User doctor=new User();
                doctor.setId(resultSet.getLong(RecipeTable.FK_DOCTOR));
                recipe.setPatient(patient);
                recipe.setDoctor(doctor);
                Medicine medicine=new Medicine();
                medicine.setId(resultSet.getLong(RecipeTable.FK_MEDICINE));
                medicine.setMedicineName(resultSet.getString(MedicineTable.MEDICINE_NAME));
                medicine.setDosage(resultSet.getInt(MedicineTable.DOSAGE));
                recipe.setMedicine(medicine);
                recipe.setMedicineQuantity(resultSet.getInt(RecipeTable.MEDICINE_QUANTITY));
                recipe.setExpired(resultSet.getBoolean(RecipeTable.EXPIRED));
                recipe.setRenewRequest(resultSet.getBoolean(RecipeTable.RENEW_REQUEST));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }


    /**
     * Method establish connection with database for deleting recipe with specified id from "recipe" table.
     * @param recipeId number that define recipe entity id that should be deleted.
     * @return true if operation of deleting was executed, false - if wasn't.
     * @throws DAOException
     */
    @Override
    public boolean delete(long recipeId) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_RECIPE_BY_ID)){
            st.setLong(1,recipeId);
            isDeleted=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isDeleted;
    }


    /**
     * Method establish connection with database to add new recipe to "recipe" table.
     * @param entity - recipe to be added to database.
     * @return true if operation of adding was executed, false - if wasn't.
     * @throws DAOException
     */
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


    /**
     * Method establish connection with database to update recipe entity in "recipe" table.
     * @param entity - recipe to be updated.
     * @return true if operation of updating was executed, false - if wasn't.
     * @throws DAOException
     */
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
