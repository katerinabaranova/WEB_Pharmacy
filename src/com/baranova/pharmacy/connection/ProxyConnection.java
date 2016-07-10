package com.baranova.pharmacy.connection;

import java.sql.*;

public class ProxyConnection implements Connection,Wrapper {
    private Connection connection;

    ProxyConnection(Connection connection) { // только в пакете
        this.connection = connection;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
    @Override
    public void close() throws SQLException {
        connection.close();
    }
    @Override
    public void commit() throws SQLException {

        connection.commit();
    }
        @Override
    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }
    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
    @Override
    public void rollback() throws SQLException {
        connection.rollback();
    }
    @Override
    public void setAutoCommit(boolean flag) throws SQLException {
        connection.setAutoCommit(flag);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }


}


