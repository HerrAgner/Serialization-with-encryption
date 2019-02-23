package Serialization;

import Encryption.Encryption;

import javax.crypto.SealedObject;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializeObject {

    public void serializeObjectToFile(Object object, Path path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serializeObjectToNetwork(ObjectOutputStream oos, Object object) {
        Encryption encryption = new Encryption();
        try {
//            byte[] data = convertToBytes(object);
            SealedObject encryptedObject = encryption.encryptObject((Serializable) object);
            oos.writeObject(encryptedObject);
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
