package com.company.library.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Reader {
    public boolean loadAppConfig(String filePath) throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream(new File(filePath)));

        MysqlConfig mysqlConfig = MysqlConfig.getInstance();
        mysqlConfig.setDsn(props.getProperty("dsn"));
        mysqlConfig.setUsername(props.getProperty("username"));
        mysqlConfig.setPassword(props.getProperty("password"));

        return true;
    }
}
