package com.company;

import com.company.library.behavior.IParser;
import com.company.library.config.Reader;
import com.company.library.mysql.query.Command;
import com.company.library.parser.Parser;
import com.company.library.socket.Server;
import com.company.library.thread.Executor;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws Throwable {
        Reader r = new Reader();
        r.loadAppConfig("/tmp/config.ini");

        Command c = new Command();
        c.addCommand("ls -alh", new Date(), "asd55555", Command.TYPE_EVERY);
/*
        // Start commands executor process!
        Executor executor = new Executor();

        // Start "executor" thread
        (new Thread(executor)).start();

        // Start socket server!
        Server.start(executor, 9634);*/
    }
}