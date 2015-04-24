package com.company.library.behavior;

import com.company.library.parser.JobItem;

import java.util.AbstractMap;
import java.util.Date;

public interface IParser {
	/**
	 * Parser command
	 * @param rawtext command text
	 */
	AbstractMap.SimpleEntry<Date, JobItem> parse(String rawtext) throws Throwable;

	/**
	 * Is successfully parsed?!
	 * 
	 * @return boolean
	 */
	boolean isSuccess();
}
