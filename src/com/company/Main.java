package com.company;

import com.company.library.behavior.IParser;
import com.company.library.parser.Parser;
import com.company.library.socket.Server;
import com.company.library.thread.Executor;

public class Main {
    public static void main(String[] args) throws Throwable {
        // Start commands executor process!
        Executor executor = new Executor();
        (new Thread(executor)).start();

        // Start socket server!
        Server.start(executor, 9634);
    }
}