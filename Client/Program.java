package Client;

import ObjectsToSend.Person;
import ObjectsToSend.Pet;
import ObjectsToSend.Shape;
import ObjectsToSend.Square;

import java.io.*;
import java.util.ArrayList;

public class Program {

    public Program() {

        //test code
        Pet pet = new Pet("Cat", "Berit", 2);
        Person person = new Person("Kent", 25);
        Square square = new Square(2, 4, 20, 20);
        Shape shape = new Shape(2, 3);
        ArrayList<Serializable> list = new ArrayList<>();
        list.add(pet);
        list.add(person);
        list.add(square);
        list.add(shape);

        Client.getInstance().getSender().sendToServer(list);
        //end of test code
    }
}

