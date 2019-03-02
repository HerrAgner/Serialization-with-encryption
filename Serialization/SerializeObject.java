package Serialization;

import Encryption.Encryption;

import javax.crypto.SealedObject;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SerializeObject {

    public static void serializeObjectToFile(Object object, String fileName, StandardOpenOption... option) {
        Path path = Paths.get("files/" + fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path, option))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeObjectToNetwork(ObjectOutputStream oos, Object object) {
        Encryption encryption = new Encryption();
        try {
//            byte[] data = convertToBytes(object);
            SealedObject encryptedObject = encryption.encryptObject((Serializable) object);
            oos.writeObject(encryptedObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void serializeObjectToNetworkSocket(Socket socket, Object object) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            serializeObjectToNetwork(oos, object);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
//    private byte[] convertToBytes(Object object) throws IOException {
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ObjectOutput out = new ObjectOutputStream(bos)) {
//            out.writeObject(object);
//            return bos.toByteArray();
//        }
//    }
}
