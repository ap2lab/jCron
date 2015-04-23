package com.company.library.config;

public class MysqlConfig {
    private String dsn;
    private String username;
    private String password;

    private static MysqlConfig instance = null;

    public static MysqlConfig getInstance() {
        if(MysqlConfig.instance == null) {
            MysqlConfig.instance = new MysqlConfig();
        }

        return MysqlConfig.instance;
    }

    /**
     * @param val dsn string
     */
    public void setDsn(String val) {
        dsn = val;
    }

    /**
     * @return String
     */
    public String getDsn() {
        return dsn;
    }

    /**
     * @param val mysql user name
     */
    public void setUsername(String val) {
        username = val;
    }

    /**
     * @return String mysql user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param val mysql password
     */
    public void setPassword(String val) {
        password = val;
    }

    /**
     * @return String mysql password
     */
    public String getPassword() {
        return password;
    }
}
