package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Order;
import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.exception.ExceptionDAO;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ekaterina on 7/15/16.
 */
public class RecipeDAO extends AbstractDAO<Recipe> {
    private static final String SQL_SELECT_ALL_RECIPES = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM recipe";
    private static final String SQL_SELECT_RECIPE_BY_ID = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM recipe WHERE idrecipe=?";
    private static final String SQL_SELECT_RECIPE_BY_PACIENT = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM recipe WHERE fkPacient=?";
    private static final String SQL_SELECT_RECIPE_BY_DOCTOR = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM recipe WHERE fkDoctor=?";
    private static final String SQL_DELETE_RECIPE_BY_ID = "DELETE FROM recipe WHERE idrecipe = ?;";
    private static final String SQL_CREATE_RECIPE = "INSERT INTO recipe(fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired) values(?,?,?,?,?);";
    private static final String SQL_UPDATE_RECIPE_BY_ENTITY="UPDATE recipe SET idrecipe=?,fkDoctor=?,fkPacient=?,fkMedicine=?,medicineQuantity=?,expired=? WHERE idrecipe=?;";

    @Override
    public List<Recipe> findAll() throws ExceptionDAO {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_ALL_RECIPES)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDoctorID(resultSet.getLong("fkDoctor"));
                recipe.setPatientID(resultSet.getLong("fkPacient"));
                recipe.setMedicineID(resultSet.getLong("fkMedicine"));
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return recipes;
    }

    @Override
    public Recipe findEntityById (long id)  throws ExceptionDAO{
        Recipe recipe = new Recipe();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_ID)){
            st.setLong(1,id);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDoctorID(resultSet.getLong("fkDoctor"));
                recipe.setPatientID(resultSet.getLong("fkPacient"));
                recipe.setMedicineID(resultSet.getLong("fkMedicine"));
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return recipe;
    }

    public List<Recipe> findRecipesByPacient(Long pacientId) throws ExceptionDAO {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PACIENT)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDoctorID(resultSet.getLong("fkDoctor"));
                recipe.setPatientID(resultSet.getLong("fkPacient"));
                recipe.setMedicineID(resultSet.getLong("fkMedicine"));
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return recipes;
    }

    public List<Recipe> findRecipesByDoctor(Long doctorId) throws ExceptionDAO {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_DOCTOR)){
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe();
                recipe.setId(resultSet.getLong("idrecipe"));
                recipe.setDoctorID(resultSet.getLong("fkDoctor"));
                recipe.setPatientID(resultSet.getLong("fkPacient"));
                recipe.setMedicineID(resultSet.getLong("fkMedicine"));
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return recipes;
    }

    @Override
    public boolean delete(long idRecipe) throws ExceptionDAO {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_RECIPE_BY_ID)){
            st.setLong(1,idRecipe);
            isDeleted=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(Recipe entity) throws ExceptionDAO {
        throw new ExceptionDAO("This operation is not available in this version");
    }

    @Override
    public boolean create(Recipe entity) throws ExceptionDAO{
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_RECIPE)){
            st.setLong(1,entity.getDoctorID());
            st.setLong(2,entity.getPatientID());
            st.setLong(3,entity.getMedicineID());
            st.setInt(4,entity.getMedicineQuantity());
            st.setBoolean(5,entity.isExpired());
            isCreated=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Recipe entity) throws ExceptionDAO {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isUpdate=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_UPDATE_RECIPE_BY_ENTITY)){
            st.setLong(1,entity.getId());
            st.setLong(2,entity.getDoctorID());
            st.setLong(3,entity.getPatientID());
            st.setLong(4,entity.getMedicineID());
            st.setInt(5,entity.getMedicineQuantity());
            st.setBoolean(6,entity.isExpired());
            st.setLong(7,entity.getId());
            isUpdate=st.execute();
        } catch (SQLException e) {
            throw new ExceptionDAO("Impossible to execute request(request or table failed):" + e);
        }
        return isUpdate;
    }
}