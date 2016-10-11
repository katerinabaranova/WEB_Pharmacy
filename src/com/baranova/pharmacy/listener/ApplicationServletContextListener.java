package com.baranova.pharmacy.listener;

import com.baranova.pharmacy.pool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ApplicationServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        connectionPool.closingPool();
    }
}
