package Server;


import Serialization.DeserializeObject;
import Serialization.SerializeObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ClientHandler implements Runnable {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket clientSocket;
    private Server serverApp;
    private boolean isRunning = false;
    private final int TIMEOUT_MS = 10;

    public ClientHandler(Socket socket, Server serverApp) {
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
        Object object = DeserializeObject.deserializeObjectFromNetwork(ois);
        System.out.println("Reading: " + object);
        SerializeObject.serializeObjectToFile(object, "networkObject.txt", StandardOpenOption.CREATE);
    }

    private void writeMessage() {
        Object object = DeserializeObject.deserializeObjectFromFile("networkObject.txt");
        System.out.println("Sending: " + object);
        SerializeObject.serializeObjectToNetwork(oos, object);
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