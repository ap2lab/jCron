package com.company.library.socket;

import com.company.library.thread.Executor;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Server {

    protected static Executor executor;
    /**
     * Start socket server
     * @param port
     */
    public static void start(Executor exe, int port)    {
        executor = exe;

        try {
            // Create socket server
            ServerSocket ss = new ServerSocket(port);

            // Start accepting clients :)
            while (true) {
                Socket s = ss.accept();
                System.err.println("Client accepted");
                new Thread(new SocketProcessor(s)).start();
            }
        }catch (Throwable t) {
        }
    }

    /**
     * Socket processor class
     */
    private static class SocketProcessor implements Runnable {
        private Socket s;
        private InputStream is;
        private OutputStream os;

        /**
         * Init variables!
         * @param s
         * @throws Throwable
         */
        private SocketProcessor(Socket s) throws Throwable {
            this.s = s;
            this.is = s.getInputStream();
            this.os = s.getOutputStream();
        }

        public void run() {
            try {
                readInputHeaders();
                writeResponse("<html><body><h1>Hello from Habrahabr</h1></body></html>");
            } catch (Throwable t) {
            } finally {
                try {
                    s.close();
                } catch (Throwable t) {
                }
            }
            System.err.println("Client processing finished");
        }

        private void writeResponse(String s) throws Throwable {
            os.write(s.getBytes());
            os.flush();
        }

        private void readInputHeaders() throws Throwable {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while(true) {
                String s = br.readLine();
                if(s == null || s.trim().length() == 0) {
                    break;
                }

                executor.processCommand(s);
            }
        }
    }
}
