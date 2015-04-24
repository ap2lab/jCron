package com.company.library.parser;

import com.company.library.mysql.query.Command;

import java.util.Date;

public class JobItem {

    private int id = 0;

    private Date executionDate;

    private String command;

    private String identifier;

    private String type;

    /**
     * Set autoincremented id
     *
     * @param val int
     * @return JobItem
     */
    public JobItem setId(int val) {
        id = val;

        return this;
    }

    /**
     * Get autoincremented id
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Set execution date
     *
     * @param val Date
     * @return JobItem
     */
    public JobItem setExecutionDate(Date val) {
        executionDate = val;

        return this;
    }

    /**
     * Get execution date
     *
     * @return Date
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * Set command string
     *
     * @param val String
     * @return JobItem
     */
    public JobItem setCommand(String val) {
        command = val;

        return this;
    }

    /**
     * Get command string
     *
     * @return String
     */
    public String getCommand() {
        return command;
    }

    /**
     * Set identifier string
     *
     * @param val String
     * @return JobItem
     */
    public JobItem setIdentifier(String val) {
        identifier = val;

        return this;
    }

    /**
     * Set type
     *
     * @param val String
     * @return JobItem
     */
    public JobItem setType(String val) {
        type = val;

        return this;
    }

    /**
     * Get type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Get identifier string
     *
     * @return String
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Save command to db!
     *
     * @throws Exception
     */
    public void save() throws Exception {
        Command c = new Command();
        int id = c.addCommand(getCommand(), getExecutionDate(), getIdentifier(), getType());
        setId(id);
    }

    public boolean remove() throws Exception {
        if (getId() == 0) {
            return false;
        }

        Command c = new Command();
        return c.removeCommandById(getId());
    }
}