package Client;


import Serialization.SerializeObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender extends Thread {
    private Socket socket;
    private ObjectOutputStream oos;

    public Sender(Socket socket) {
        this.socket = socket;
        try {
            oos = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("failed to create stream");
            Client.getInstance().isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(Object object) {
        SerializeObject.serializeObjectToNetwork(oos, object);
    }
}