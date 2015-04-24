package com.company.library.thread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.company.library.parser.JobItem;
import com.company.library.parser.Parser;

public class Executor extends Thread {

    /**
     * Commands container
     */
    protected static HashMap<Date, ArrayList<JobItem>> oneTimeCommands = new HashMap<Date, ArrayList<JobItem>>();

    /**
     * Add command to commands list
     *
     * @param date Date
     * @param job  JobItem
     * @return boolean
     */
    protected boolean addCommand(Date date, JobItem job) {
        ArrayList<JobItem> dCommands = new ArrayList<JobItem>();
        if (Executor.oneTimeCommands.containsKey(date)) {
            dCommands = Executor.oneTimeCommands.get(date);
        }

        try {
            job.save();
            dCommands.add(job);
            Executor.oneTimeCommands.put(date, dCommands);
            return true;
        }catch(Exception exception) {
            return false;
        }
    }

    /**
     * Get date commands
     *
     * @param date Date
     * @return ArrayList
     */
    public ArrayList<JobItem> commands(Date date) {
        if (!Executor.oneTimeCommands.containsKey(date)) {
            return null;
        }

        return Executor.oneTimeCommands.get(date);
    }

    /**
     * @param command String
     * @return boolean
     */
    public boolean processCommand(String command) throws Throwable {
        AbstractMap.SimpleEntry<Date, JobItem> pair = Parser.parseIt(command);
        if(pair == null) {
            throw new Throwable();
        }

        return addCommand(pair.getKey(), pair.getValue());
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

            if (Executor.oneTimeCommands.containsKey(dateWithoutTime)) {
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
     * @param commands list
     */
    public static void executeCommand(ArrayList<JobItem> commands) {
        try {
            for (JobItem cmd : commands) {
                System.out.println(cmd);

                // Run ls command
                Process process = Runtime.getRuntime().exec(cmd.getCommand());
                OutputStream output = process.getOutputStream();
                process.waitFor();

                // Remove from db "one time executed command"
                cmd.remove();

                PrintStream prtStrm = System.out;
                prtStrm = new PrintStream(output);
                prtStrm.println();
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}
