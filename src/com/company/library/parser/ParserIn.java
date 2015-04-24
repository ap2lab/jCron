package com.company.library.parser;

import com.company.library.behavior.IParser;

import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserIn extends Parser implements IParser {
    protected boolean isSuccess = false;

    /**
     * Get execution time and command from raw string
     *
     * @param rawtext raw string to parse
     * @return AbstractMap.SimpleEntry<>
     * @throws Throwable
     */
    public AbstractMap.SimpleEntry<Date, JobItem> parse(String rawtext) throws Throwable {
        Matcher matcher;

        if (rawtext.matches(Parser.patternIn1)) {
            Pattern pattern = Pattern.compile(Parser.patternIn1);
            matcher = pattern.matcher(rawtext);

            return getModelFromPattern1(matcher);
        }

        if (rawtext.matches(Parser.patternIn2)) {
            Pattern pattern = Pattern.compile(Parser.patternIn2);
            matcher = pattern.matcher(rawtext);

            return getModelFromPattern2(matcher);
        }

        if (rawtext.matches(Parser.patternIn3)) {
            Pattern pattern = Pattern.compile(Parser.patternIn3);
            matcher = pattern.matcher(rawtext);

            return getModelFromPattern3(matcher);
        }


        return null;
    }

    /**
     * Get date&command from patter1
     *
     * @param matcher pattern1 matcher
     * @return AbstractMap.SimpleEntry<>
     */
    public AbstractMap.SimpleEntry<Date, JobItem> getModelFromPattern1(Matcher matcher) {

        // Find matches for pattern 1
        if (!matcher.find()) {
            throw new IllegalStateException();
        }

        // Calc execution time!
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String p = matcher.group(1);
        if (p.equals("a minute")) {
            cal.add(Calendar.MINUTE, 1);
        } else if (p.equals("an hour")) {
            cal.add(Calendar.HOUR, 1);
        } else if (p.equals("a day")) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
        } else if (p.equals("a week")) {
            cal.add(Calendar.WEEK_OF_MONTH, 1);
        } else if (p.equals("a month")) {
            cal.add(Calendar.MONTH, 1);
        } else if (p.equals("a year")) {
            cal.add(Calendar.YEAR, 1);
        }
        Date executionDate = cal.getTime();

        // Parse identifier
        String[] parts = matcher.group(3).split(" ");

        // Create & fill model
        JobItem jobItem = new JobItem();
        jobItem.setExecutionDate(executionDate).setCommand(matcher.group(2)).setIdentifier(parts[1]);
        // Result!
        return new AbstractMap.SimpleEntry<Date, JobItem>(executionDate, jobItem);
    }

    /**
     * Get date&command from patter2
     *
     * @param matcher pattern2 matcher
     * @return AbstractMap.SimpleEntry<>
     */
    public AbstractMap.SimpleEntry<Date, JobItem> getModelFromPattern2(Matcher matcher) {
        // Find matches for pattern 1
        if (!matcher.find()) {
            throw new IllegalStateException();
        }

        // Calc execution time!
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String p = matcher.group(2);
        int i = Integer.parseInt(matcher.group(1));
        if (p.equals("minutes")) {
            cal.add(Calendar.MINUTE, i);
        } else if (p.equals("hours")) {
            cal.add(Calendar.HOUR, i);
        } else if (p.equals("days")) {
            cal.add(Calendar.DAY_OF_WEEK, i);
        } else if (p.equals("weeks")) {
            cal.add(Calendar.WEEK_OF_MONTH, i);
        } else if (p.equals("months")) {
            cal.add(Calendar.MONTH, i);
        } else if (p.equals("years")) {
            cal.add(Calendar.YEAR, i);
        }
        Date executionDate = cal.getTime();

        // Parse identifier
        String[] parts = matcher.group(3).split(" ");

        // Create & fill model
        JobItem jobItem = new JobItem();
        jobItem.setExecutionDate(executionDate).setCommand(matcher.group(2)).setIdentifier(parts[1]);
        // Result!
        return new AbstractMap.SimpleEntry<Date, JobItem>(executionDate, jobItem);
    }

    /**
     * Get date&command from patter3
     *
     * @param matcher pattern3 matcher
     * @return AbstractMap.SimpleEntry<>
     */
    public AbstractMap.SimpleEntry<Date, JobItem> getModelFromPattern3(Matcher matcher) {
        // Find matches for pattern 1
        if (!matcher.find()) {
            throw new IllegalStateException();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        for (int i = 1; i <= matcher.groupCount() - 1; i++) {
            String s = matcher.group(i);

            if (s == null) {
                continue;
            }

            String last = s.substring(s.length() - 1);
            int count = Integer.parseInt(s.substring(0, s.length() - 1));

            if (last.equals("M")) {
                cal.add(Calendar.MINUTE, count);
            } else if (last.equals("H")) {
                cal.add(Calendar.HOUR, count);
            } else if (last.equals("D")) {
                cal.add(Calendar.DAY_OF_WEEK, count);
            } else if (last.equals("W")) {
                cal.add(Calendar.WEEK_OF_MONTH, count);
            } else if (last.equals("M")) {
                cal.add(Calendar.MONTH, count);
            } else if (last.equals("Y")) {
                cal.add(Calendar.YEAR, count);
            }
        }

        Date executionDate = cal.getTime();

        // Parse identifier
        String[] parts = matcher.group(3).split(" ");

        // Create & fill model
        JobItem jobItem = new JobItem();
        jobItem.setExecutionDate(executionDate).setCommand(matcher.group(2)).setIdentifier(parts[1]);
        // Result!
        return new AbstractMap.SimpleEntry<Date, JobItem>(executionDate, jobItem);
    }

    /**
     * Is success parsed?!
     *
     * @return boolean
     */
    public boolean isSuccess() {
        return isSuccess;
    }
}
