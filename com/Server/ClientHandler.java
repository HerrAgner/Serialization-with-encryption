package com.Server;


import com.Serialization.DeserializeObject;
import com.Serialization.SerializeObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSocket;
    private Server serverApp;
    private SerializeObject serialize;
    private DeserializeObject deserialize;
    private boolean isRunning = false;
    private final int TIMEOUT_MS = 1;

    public ClientHandler(Socket socket, Server serverApp) {
        this.serialize = new SerializeObject();
        this.deserialize = new DeserializeObject();
        this.clientSocket = socket;
        this.serverApp = serverApp;

        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            clientSocket.setSoTimeout(TIMEOUT_MS);
            isRunning = true;
        } catch (IOException e) {
            System.out.println("failed to create streams ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(socket.getInetAddress().toString() + " connected");
    }

    private void readMessage() {
        Object object = deserialize.deserializeObjectFromNetwork(ois);
        System.out.println("Reading: " + object);
        serialize.serializeObjectToFile(object, Paths.get("networkObject.txt"));
    }

    private void writeMessage() {
        Object object = deserialize.deserializeObjectFromFile(Paths.get("networkObject.txt"));
        System.out.println("Sending: " + object);
        serialize.serializeObjectToNetwork(oos, object);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readMessage();
        writeMessage();
    }
}