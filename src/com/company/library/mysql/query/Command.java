package com.company.library.mysql.query;


import com.company.library.mysql.MySQLAccess;
import com.company.library.parser.Parser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Command extends MySQLAccess {

    public static final String TYPE_ONE = "one";
    public static final String TYPE_EVERY = "every";

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    /**
     * @param command    command string
     * @param date       execution date
     * @param identifier identifier string
     */
    public void addCommand(String command, Date date, String identifier, String type) throws Exception {
        preparedStatement = super.getConnection()
                .prepareStatement("INSERT INTO command SET command=?, `execution_date`=?, identifier=?, `type`=?");

        DateFormat df = new SimpleDateFormat(Parser.DATE_MYSQL_FORMAT);
        String dateString = df.format(date);

        preparedStatement.setString(1, command);
        preparedStatement.setString(2, dateString);
        preparedStatement.setString(3, identifier);
        preparedStatement.setString(4, type);

        preparedStatement.executeUpdate();

    }
}
