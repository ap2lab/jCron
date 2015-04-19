package com.company.library.thread;

import java.util.*;

import com.company.library.parser.Parser;
import com.company.library.behavior.IParser;

public class Executor extends Thread {

    /**
     * Commands container
     */
    protected static HashMap<Date, ArrayList<String>> hm = new HashMap<Date, ArrayList<String>>();

    /**
     * Add command to commands list
     *
     * @param date
     * @param command
     * @return boolean
     */
    protected boolean addCommand(Date date, String command) {
        ArrayList<String> dCommands = new ArrayList<String>();
        if (Executor.hm.containsKey(date)) {
            dCommands = Executor.hm.get(date);
        }
        dCommands.add(command);

        Executor.hm.put(date, dCommands);

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
        if(!Executor.hm.containsKey(date)) {
            return false;
        }

        ArrayList<String> dCommands = Executor.hm.get(date);
        return dCommands.remove(string);
    }

    /**
     * Get date commands
     *
     * @param date
     * @return ArrayList
     */
    public ArrayList<String> commands(Date date) {
        if(!Executor.hm.containsKey(date)) {
            return null;
        }

        return Executor.hm.get(date);
    }

    public boolean processCommand(String command) {
        try {
            AbstractMap.SimpleEntry<Date, String> pair = Parser.parseIt(command);
            addCommand(pair.getKey(), pair.getValue());
        }catch (Throwable t) {
            return false;
        }
        return true;
    }

    public void run() {
        while(true) {
            Date d = new Date();

            Iterator it = Executor.hm.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println(pair.getKey() + " = " + pair.getValue());
            }

            try {
                sleep(60 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
