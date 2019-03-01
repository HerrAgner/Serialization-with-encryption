package Serialization;

import Encryption.Decryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeserializeObject {

    public static Object deserializeObjectFromFile(String fileName) {
        Path path = Paths.get(fileName);
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object deserializeObjectFromNetwork(ObjectInputStream ois) {
        Decryption decryption = new Decryption();
        try {
            Object object = ois.readObject();
            if (object instanceof SealedObject) {
                object = ((SealedObject) object).getObject(decryption.decrypt());
//                o = convertFromBytes((byte[]) o);
                return object;
            }
            return object;
        } catch (BadPaddingException e) {
            System.out.println("Wrong key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object deserializeObjectFromNetworkSocket(Socket socket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return deserializeObjectFromNetwork(ois);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    private Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
//        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
//             ObjectInput in = new ObjectInputStream(bis)) {
//            return in.readObject();
//        }
//    }
}
