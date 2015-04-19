package com.company.library.behavior;

import java.util.AbstractMap;
import java.util.Date;

public interface IParser {
	/**
	 * Parser command
	 * @param rawtext
	 */
	AbstractMap.SimpleEntry<Date, String> parse(String rawtext) throws Throwable;

	/**
	 * Is successfully parsed?!
	 * 
	 * @return boolean
	 */
	boolean isSuccess();
}
