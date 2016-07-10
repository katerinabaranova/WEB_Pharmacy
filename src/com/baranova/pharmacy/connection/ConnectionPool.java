package com.baranova.pharmacy.connection;

import com.baranova.pharmacy.constant.FileConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool<T> {
    private static final Logger LOG= LogManager.getLogger();
    private static ConnectionPool instance=null;
    private volatile static boolean instanceCreated=false;
    private static Lock lock=new ReentrantLock();
    private ArrayBlockingQueue <T> connectionQueue;

    private ConnectionPool (final int POOL_SIZE){
        connectionQueue=new ArrayBlockingQueue<T>(POOL_SIZE);
        ResourceBundle resource = ResourceBundle.getBundle(FileConstant.DATABASE_INFO);
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                //TODO можно ли так делать
                T connection = (T) DriverManager.getConnection(url, user, pass);
                connectionQueue.offer(connection);
            } catch (SQLException e) {
                LOG.error("Creating connection is failed");
            }
        }
    }
    public static ConnectionPool getInstance(final int POOL_SIZE){
        if (!instanceCreated) {
            lock.lock();
            try {
                instance = new ConnectionPool(POOL_SIZE);
                instanceCreated = true;
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public T  getConnection() {
        T connection=null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e){
            LOG.error("Interrupted error");
        }
        return connection;
    }

    public void closeConnection(T connection){
        //TODO возможны утечки??? что исправить
        connectionQueue.offer(connection);
    }


}
