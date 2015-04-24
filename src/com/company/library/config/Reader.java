package com.company.library.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reader {

    /**
     * Load application configuration file
     *
     * @param filePath configuration file path
     * @throws IOException
     */
    public void loadAppConfig(String filePath) throws IOException {

        // Load .ini file
        Properties props = new Properties();
        props.load(new FileInputStream(new File(filePath)));

        // Mysql parameters
        MysqlConfig mysqlConfig = MysqlConfig.getInstance();
        mysqlConfig.setDsn(props.getProperty("dsn"));
        mysqlConfig.setUsername(props.getProperty("username"));
        mysqlConfig.setPassword(props.getProperty("password"));
    }
}
