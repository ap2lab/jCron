package com.company.library.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.library.behavior.IParser;

public class Parser {
    public static final String DATE_MYSQL_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     *
     * @param command
     * @return
     */
    public static AbstractMap.SimpleEntry<Date, String> parseIt(String command) {
        try {
            return Parser.getParser(command).parse(command);
        } catch (Throwable t) {
            return null;
        }
    }

    /**
     * Detect & return parser for corresponding type
     *
     * @param query
     * @return IParser
     * @throws Throwable
     */
    public static IParser getParser(String query) throws Throwable {

        // Example - at: 2015-04-05 18:14:07 => ls -alh
        final String patternAt = "at:\\s?\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s?=>\\s?(.+)";
        if (query.matches(patternAt)) {
            System.out.println("Parser AT detected");
            return new ParserAt();
        }

        return null;
    }


    /**
     * Check is valid date string
     *
     * @param dateString
     * @return boolean
     */
    public Date isValidDate(String dateString, String format) {
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(dateString);
        } catch (ParseException exception) {
            return null;
        }
    }
}
