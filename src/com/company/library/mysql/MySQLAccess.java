package com.company.library.mysql;

import com.company.library.config.MysqlConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Formatter;

public class MySQLAccess {
    public Connection getConnection() throws Exception {

        MysqlConfig mysqlConfig = MysqlConfig.getInstance();

        String connectionString = String.format("%s?user=%s&password=%s",
                mysqlConfig.getDsn(),
                mysqlConfig.getUsername(),
                mysqlConfig.getPassword()
        );

        return DriverManager.getConnection(connectionString);
    }
}
