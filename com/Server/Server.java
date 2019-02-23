package com.Server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private final int PORT = 25252;
    private ExecutorService clientHandlers;
    private boolean running = false;

    public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
        } catch (IOException e) {
            System.out.println("Could not create a server");
        } catch (Exception e) {
            e.printStackTrace();
        }
        clientHandlers = Executors.newCachedThreadPool();

    }

    public boolean isRunning() {
        return running;
    }



    void kill() {
        clientHandlers.shutdown();
        running = false;
    }

    public void run() {
        if (running) {
            System.out.println("Server is running on port " + PORT);
        }
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                clientHandlers.submit(new ClientHandler(socket, this));
            } catch (IOException e) {
                System.out.println("Fail to connect to client");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

















