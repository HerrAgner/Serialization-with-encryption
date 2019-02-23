package Client;

import java.net.Socket;

public class Client {
    private static Client ourInstance = new Client();

    public static Client getInstance() {
        return ourInstance;
    }

    public boolean isRunning;
    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    private Client() {
        try {
            socket = new Socket("localhost", 25252);
            isRunning = true;
            sender = new Sender(socket);
            receiver = new Receiver(socket);
            sender.start();
            receiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sender getSender() {
        return sender;
    }

    public void kill() {
        isRunning = false;
    }
}