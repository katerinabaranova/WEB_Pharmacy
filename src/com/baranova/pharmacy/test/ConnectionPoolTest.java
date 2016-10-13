package com.baranova.pharmacy.test;

import com.baranova.pharmacy.constant.FileConstant;
import com.baranova.pharmacy.pool.ConnectionPool;
import com.baranova.pharmacy.pool.ProxyConnection;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * JUnit test for Connection pool class
 */
public class ConnectionPoolTest {
    public final static String DATABASE_FILE="src/resource/database.properties";

    @Test
    public void PropertyFileExistTest(){
        File file=new File(DATABASE_FILE);
        assertTrue(file.exists());
    }

    @Test
    public void ConnectionPoolCreateTest(){
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Assert.assertNotNull(connectionPool);
    }

    @Test(timeout=5000)
    public void takeReleaseConnectionTest(){
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        ProxyConnection proxyConnection=connectionPool.takeConnection();
        connectionPool.releaseConnection(proxyConnection);

    }

}
