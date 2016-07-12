package com.baranova.pharmacy.factory;

import com.baranova.pharmacy.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityFactory {
    private static final Logger LOG= LogManager.getLogger();

    public User createUser(ResultSet resultSet){

        return new User();
    }
}
