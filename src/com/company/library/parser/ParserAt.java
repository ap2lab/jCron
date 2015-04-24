package com.company.library.parser;

import com.company.library.behavior.IParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserAt extends Parser implements IParser {

    protected boolean isSuccess = false;

    /**
     * parse "at" command
     *
     * @param rawtext command text
     * @return AbstractMap.SimpleEntry<~>
     * @throws Throwable
     */
    public AbstractMap.SimpleEntry<Date, JobItem> parse(String rawtext) throws Throwable {
        // Match "at" pattern string to get parts
        Pattern pattern = Pattern.compile(Parser.patternAt);
        Matcher matcher = pattern.matcher(rawtext);
        if (!matcher.find()) {
            isSuccess = false;
            throw new Throwable();
        }

        // String to date
        DateFormat format = new SimpleDateFormat(Parser.DATE_MYSQL_FORMAT);
        Date date = format.parse(matcher.group(1));
        if (date.before(new Date())) {
            return null;
        }

        // Parse identifier
        String[] parts = matcher.group(3).split(" ");

        // Create & fill cronjob model
        JobItem job = new JobItem();
        job.setExecutionDate(date).setCommand(matcher.group(2)).setIdentifier(parts[1]);

        // Set successfully parsed!
        isSuccess = true;

        return new AbstractMap.SimpleEntry<Date, JobItem>(date, job);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
