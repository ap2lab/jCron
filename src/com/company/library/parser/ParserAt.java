package com.company.library.parser;

import com.company.library.behavior.IParser;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParserAt extends Parser implements IParser {

    protected boolean isSuccess = false;

    public AbstractMap.SimpleEntry<Date, String> parse(String rawtext) throws Throwable {
        // Split query string by :
        String delimiters = "at:\\s?|\\s?=>\\s?";
        String[] tokensVal = rawtext.split(delimiters);

        String dateString = tokensVal[1].trim();
        String command = tokensVal[2].trim();

        Date date = super.isValidDate(dateString, Parser.DATE_MYSQL_FORMAT);
        if (date == null) {
            throw new Error("Invalid date provided");
        }

        isSuccess = true;

        return new AbstractMap.SimpleEntry<Date, String>(date, command);
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
