package com.baranova.pharmacy.service;


import com.baranova.pharmacy.dao.UserDAO;
import com.baranova.pharmacy.entity.User;
import com.baranova.pharmacy.exception.ExceptionDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ekaterina on 7/20/16.
 */
public class Service {
    private static final Logger LOG= LogManager.getLogger();

    public static boolean checkLogingInfo(String login, String password){
        try {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.findEntityByLogin(login);
            String passwordCheck="";
            if (user.getLogin() != null) {
                passwordCheck=user.getPassword();
            }
            if (Service.getMD5(password).equals(passwordCheck)){
                return true;
            } else {
                return false;
            }
        } catch (ExceptionDAO e){
            LOG.error(e.getMessage());
        }
        return false;
    }

    public static String getMD5(String password) {
        String hashPassword="";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passwordDigest=md.digest(password.getBytes("UTF-8"));
            BigInteger bigInteger=new BigInteger(1,passwordDigest);
            hashPassword=bigInteger.toString(16);
            while (hashPassword.length()<32){
                hashPassword="0"+hashPassword;
            }
        } catch (NoSuchAlgorithmException e){
            LOG.error("Wrong name of digest algorithm");
        } catch (UnsupportedEncodingException e){
            LOG.error("Impossible to encode password");
        }
        return hashPassword;
    }
}
