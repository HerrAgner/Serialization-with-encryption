package com.Client;

import com.ObjectsToSend.Person;
import com.ObjectsToSend.Pet;
import com.ObjectsToSend.Square;
import com.Serialization.DeserializeObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Collection;

public class Receiver extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private DeserializeObject deserialize;

    public Receiver(Socket socket) {
        this.socket = socket;
        this.deserialize = new DeserializeObject();
        try {
            this.ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("failed to create input stream");
            Client.getInstance().isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (Client.getInstance().isRunning) {
            Object objectFromNetwork = deserialize.deserializeObjectFromNetwork(ois);

            // Test code - printing with cast to different objects
            if (objectFromNetwork instanceof Collection) {
                ((Collection) objectFromNetwork).forEach(s -> {
                    if (s instanceof Pet) {
                        System.out.printf("Class: %s\nType: %s\nName: %s\nAge: %d\n\n"
                                , s.getClass()
                                , ((Pet) s).getType()
                                , ((Pet) s).getName()
                                , ((Pet) s).getAge());
                    } else if (s instanceof Person) {
                        System.out.printf("Class: %s\nName: %s\nAge: %d\n\n"
                                , s.getClass()
                                , ((Person) s).getName()
                                , ((Person) s).getAge());
                    } else if (s instanceof Square) {
                        System.out.printf("Class: %s\nx: %d\ny: %d\nWidth: %d\nHeight: %d\n\n"
                                , s.getClass()
                                , ((Square) s).getX()
                                , ((Square) s).getY()
                                , ((Square) s).getWidth()
                                , ((Square) s).getHeight());
                    } else {
                        System.out.printf("%s\n\n", s.toString());
                    }
                });
                // If object isn't a Collection, just an object
            } else {
                if (objectFromNetwork instanceof Person) {
                    Person person = (Person) objectFromNetwork;
                    System.out.println(person.getName());
                } else {
                    System.out.println(objectFromNetwork);
                }

            }
        }
    }
}