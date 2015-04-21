package com.company.library.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.library.parser.Parser;

public class Executor extends Thread {

    /**
     * Commands container
     */
    protected static HashMap<Date, ArrayList<String>> oneTimeCommands = new HashMap<Date, ArrayList<String>>();

    /**
     * Add command to commands list
     *
     * @param date
     * @param command
     * @return boolean
     */
    protected boolean addCommand(Date date, String command) {
        ArrayList<String> dCommands = new ArrayList<String>();
        if (Executor.oneTimeCommands.containsKey(date)) {
            dCommands = Executor.oneTimeCommands.get(date);
        }
        dCommands.add(command);
        Executor.oneTimeCommands.put(date, dCommands);

        return true;
    }

    /**
     * Remove command from commands list
     *
     * @param date
     * @param string
     * @return boolean
     */
    public boolean removeCommand(Date date, String string) {
        if (!Executor.oneTimeCommands.containsKey(date)) {
            return false;
        }

        ArrayList<String> dCommands = Executor.oneTimeCommands.get(date);
        return dCommands.remove(string);
    }

    /**
     * Get date commands
     *
     * @param date
     * @return ArrayList
     */
    public ArrayList<String> commands(Date date) {
        if (!Executor.oneTimeCommands.containsKey(date)) {
            return null;
        }

        return Executor.oneTimeCommands.get(date);
    }

    /**
     * @param command
     * @return
     */
    public boolean processCommand(String command) {
        try {
            AbstractMap.SimpleEntry<Date, String> pair = Parser.parseIt(command);
            addCommand(pair.getKey(), pair.getValue());
        } catch (Throwable t) {
            return false;
        }
        return true;
    }

    public void run() {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat(Parser.DATE_MYSQL_FORMAT);
            Date dateWithoutTime;

            try {
                dateWithoutTime = sdf.parse(sdf.format(new Date()));
            } catch (ParseException exception) {
                continue;
            }

            System.out.println("============== Iteration ==============");
            System.out.println(Executor.oneTimeCommands);
            System.out.println(dateWithoutTime);
            System.out.println("=======================================");

            if(Executor.oneTimeCommands.containsKey(dateWithoutTime)) {
                System.out.println("Commands found for : " + dateWithoutTime);
                executeCommand(Executor.oneTimeCommands.get(dateWithoutTime));
                Executor.oneTimeCommands.remove(dateWithoutTime);
            }

            try {
                sleep(30 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * Execute shell command
     *
     * @param commands
     */
    public static void executeCommand(ArrayList<String> commands) {
        try {
            for (String cmd : commands) {
                System.out.println(cmd);

                // Run ls command
                Process process = Runtime.getRuntime().exec(cmd);
                OutputStream output = process.getOutputStream();
                process.waitFor();

                PrintStream prtStrm = System.out;
                prtStrm = new PrintStream(output);
                prtStrm.println();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
