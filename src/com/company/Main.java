package com.company;

import com.company.library.config.Reader;
import com.company.library.socket.Server;
import com.company.library.thread.Executor;

public class Main {
    public static void main(String[] args) throws Throwable {

        // Load application configuration
        Reader r = new Reader();
        r.loadAppConfig("/tmp/config.ini");

        // Start commands executor process!
        Executor executor = new Executor();

        // Start "executor" thread
        (new Thread(executor)).start();

        // Start socket server!
        Server.start(executor, 9634);
    }
}