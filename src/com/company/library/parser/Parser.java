package com.company.library.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.library.behavior.IParser;

public class Parser {
    public static final String DATE_MYSQL_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * "at" command pattern
     */
    final static String patternAt = "at:\\s?(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\s?=>\\s?(.+)\\s?(identifier(.+))";

    /**
     * "in" command matche patterns
     */
    final static String patternIn1 = "in:\\s?(a minute|an hour|a day|a week|a month|a year)\\s?=>\\s?(.+)\\s(identifier(.+))";
    final static String patternIn2 = "in:\\s?(\\d+)\\s(minutes|hours|days|weeks|months|years)\\s?=>\\s?(.+)(identifier(.+))";
    final static String patternIn3 = "in:\\s?(\\d+Y)?(\\d+M)?(\\d+D)?(\\d+H)?(\\d+M)?\\s?=>\\s?(.+)(identifier(.+))";

    /**
     * @param command command string
     * @return AbstractMap.SimpleEntry<~>
     */
    public static AbstractMap.SimpleEntry<Date, JobItem> parseIt(String command) throws Throwable {
        return Parser.getParser(command).parse(command);
    }

    /**
     * Detect & return parser for corresponding type
     *
     * @param query query string
     * @return IParser
     * @throws Throwable
     */
    public static IParser getParser(String query) throws Throwable {

        // "At" parser
        // Example - at: 2015-04-05 18:14:07 => ls -alh identifier asda5sd4asd4asd547asd4
        if (query.matches(Parser.patternAt)) {
            System.out.println("Parser \"at\" detected");
            return new ParserAt();
        }

        // "In" parser
        /**
         * in command example
         * in: a minute, 5 minutes, 20 minutes              => command
         * in: an hour, an half of hour, 4 hours, 14 hours  => command
         * in: a week, 3 weeks, 7 weeks                     => command
         * in: a month, 4 months, 8 months                  => command
         * in: a year, 3 years , 15 years                   => command
         *
         * common interval specification:
         *     Example: 2Y3M4D7H2M1S
         *     ####################################################################################################
         */

        if (query.matches(Parser.patternIn1) || query.matches(Parser.patternIn2) || query.matches(Parser.patternIn3)) {
            System.out.println("Parser \"in\" detected");
            return new ParserIn();
        }

        return null;
    }


    /**
     * Check is valid date string
     *
     * @param dateString date string
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
