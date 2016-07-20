package com.baranova.pharmacy.pool;

import com.baranova.pharmacy.constant.FileConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static final Logger LOG= LogManager.getLogger();
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated=new AtomicBoolean(false);
    private static Lock lock=new ReentrantLock();
    private ArrayBlockingQueue <ProxyConnection> connectionQueue;

    private ConnectionPool (){
        String path=FileConstant.DATABASE_INFO;
        try {
            ResourceBundle resource = ResourceBundle.getBundle(path);
            Properties prop = new Properties();
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            prop.put("user", resource.getString("db.user"));
            prop.put("password", resource.getString("db.password"));
            prop.put("autoReconnect", resource.getString("db.autoreconnect"));
            prop.put("characterEncoding",resource.getString("db.encoding") );
            prop.put("useUnicode",resource.getString("db.useUnicode"));
            String url = resource.getString("db.url");
            int size = Integer.parseInt(resource.getString("db.poolsize"));
            connectionQueue = new ArrayBlockingQueue<ProxyConnection>(size);
            for (int i = 0; i < size; i++) {
                Connection connection = DriverManager.getConnection(url, prop);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                connectionQueue.offer(proxyConnection);
            }
        } catch (SQLException |MissingResourceException e) {
            LOG.fatal("Impossible to connect to database");
            throw new RuntimeException();
        }
    }

    public static ConnectionPool getInstance(){
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                instance = new ConnectionPool();
                instanceCreated.getAndSet(true);
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection takeConnection() {
        ProxyConnection connection=null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e){
            LOG.error("Interrupted error");
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection){
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
            }
            connectionQueue.put(connection);
        } catch (SQLException | InterruptedException e) {
            LOG.error(e);
        }
    }

    public void closingPool(){
        try {
            for (ProxyConnection connection : connectionQueue) {
                ProxyConnection proxyConnection = connectionQueue.take();
                proxyConnection.realClose();
            }
        }catch (InterruptedException e){
            LOG.error("Cannot close conection with database");
        }
    }
}
