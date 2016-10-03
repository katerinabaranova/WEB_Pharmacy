package com.baranova.pharmacy.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class generator for MD5 hashcode
 */
public class Security {

    private static final Logger LOG= LogManager.getLogger();

    /**
     * Generation MD5 hashcode from user password for adding to database
     * @param password user password that should be encoded
     * @return hashcode of password
     */
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
