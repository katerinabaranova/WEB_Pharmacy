package com.baranova.pharmacy.dao;

import com.baranova.pharmacy.entity.Recipe;
import com.baranova.pharmacy.exception.DAOException;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RecipeDAO extends AbstractDAO<Recipe> {

    private static final String SQL_SELECT_ALL_RECIPES = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM pharmacy.recipe";
    private static final String SQL_SELECT_RECIPE_BY_ID = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM pharmacy.recipe WHERE idrecipe=?";
    private static final String SQL_SELECT_RECIPE_BY_PACIENT = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM pharmacy.recipe WHERE fkPacient=?";
    private static final String SQL_SELECT_RECIPE_BY_DOCTOR = "SELECT idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired FROM pharmacy.recipe WHERE fkDoctor=?";
    private static final String SQL_DELETE_RECIPE_BY_ID = "DELETE FROM pharmacy.recipe WHERE idrecipe = ?;";
    private static final String SQL_CREATE_RECIPE = "INSERT INTO pharmacy.recipe(idrecipe,fkDoctor,fkPacient,fkMedicine,medicineQuantity,expired) values(?,?,?,?,?,?);";
    private static final String SQL_UPDATE_RECIPE_BY_ENTITY="UPDATE pharmacy.recipe SET idrecipe=?,fkDoctor=?,fkPacient=?,fkMedicine=?,medicineQuantity=?,expired=? WHERE idrecipe=?;";

    @Override
    public List<Recipe> findAll() throws DAOException {
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
                recipe.setDoctorID(resultSet.getLong("fkDoctor"));
                recipe.setPatientID(resultSet.getLong("fkPacient"));
                recipe.setMedicineID(resultSet.getLong("fkMedicine"));
                recipe.setMedicineQuantity(resultSet.getInt("medicineQuantity"));
                recipe.setExpired(resultSet.getBoolean("expired"));
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipe;
    }

    public List<Recipe> findRecipesByPacient(Long pacientId) throws DAOException {
        List<Recipe> recipes = new ArrayList<>();
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        try (ProxyConnection cn=connectionPool.takeConnection(); PreparedStatement st=cn.prepareStatement(SQL_SELECT_RECIPE_BY_PACIENT)){
            st.setLong(1,pacientId);
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
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    public List<Recipe> findRecipesByDoctor(Long doctorId) throws DAOException {
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
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return recipes;
    }

    @Override
    public boolean delete(long idRecipe) throws DAOException {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isDeleted=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_DELETE_RECIPE_BY_ID)){
            st.setLong(1,idRecipe);
            isDeleted=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isDeleted;
    }

    @Override
    public boolean delete(Recipe entity) throws DAOException {
        throw new DAOException("This operation is not available in this version");
    }

    @Override
    public boolean create(Recipe entity) throws DAOException {
        System.out.println(entity);
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        boolean isCreated=false;
        try (ProxyConnection cn=connectionPool.takeConnection();PreparedStatement st=cn.prepareStatement(SQL_CREATE_RECIPE)){

            st.setLong(1,entity.getId());
            st.setLong(2,entity.getDoctorID());
            st.setLong(3,entity.getPatientID());
            st.setLong(4,entity.getMedicineID());
            st.setInt(5,entity.getMedicineQuantity());
            st.setBoolean(6,entity.isExpired());
            isCreated=0<st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isCreated;
    }

    @Override
    public boolean update(Recipe entity) throws DAOException {
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
            isUpdate=0<st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Impossible to execute request(request or table 'Recipe' failed):", e);
        }
        return isUpdate;
    }
}
