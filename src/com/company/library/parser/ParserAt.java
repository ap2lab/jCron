package com.company.library.parser;

import com.company.library.behavior.IParser;

import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParserAt extends Parser implements IParser {
	
	protected boolean isSuccess;
	
	public AbstractMap.SimpleEntry<Date, String> parse(String rawtext) throws Throwable
	{
		// Split query string by :
		String[] parts = rawtext.split("at:");
		String dateString = parts[1].trim();

		Date date = super.isValidDate(dateString, Parser.DATE_MYSQL_FORMAT);
		if(date == null) {
			throw new Error("Invalid date provided");
		}

		return new AbstractMap.SimpleEntry<Date, String>(date, dateString);
	}

	public boolean isSuccess() {
		return false;
	}
}
