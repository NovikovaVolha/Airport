package com.academy.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceManager {

    private static DataSourceManager instance = new DataSourceManager();
    private  final ComboPooledDataSource comboPooledDataSource;

    public static DataSourceManager getInstance() {
        return instance == null ? new DataSourceManager() : instance;
    }

    public DataSourceManager() {
        var properties = new Properties();

        try (InputStream inputStream = new FileInputStream("src/main/resources/db.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        comboPooledDataSource = new ComboPooledDataSource();

        comboPooledDataSource.setJdbcUrl(properties.getProperty("db.url"));
        comboPooledDataSource.setUser(properties.getProperty("db.user"));
        comboPooledDataSource.setPassword(properties.getProperty("db.password"));

        comboPooledDataSource.setInitialPoolSize(5);
    }

    public Connection getConnection(){
        Connection connection = null;

        try {
            connection = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
