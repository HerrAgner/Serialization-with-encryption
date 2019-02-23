package Serialization;

import Encryption.Decryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeserializeObject {

    public Object deserializeObjectFromFile(Path path) {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object deserializeObjectFromNetwork(ObjectInputStream ois) {
        Decryption decryption = new Decryption();
        try {
            Object object = ois.readObject();
            if (object instanceof SealedObject) {
                object = ((SealedObject) object).getObject(decryption.decrypt());
//                o = convertFromBytes((byte[]) o);
                return object;
            }
            return object;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } catch (BadPaddingException e) {
            System.out.println("Wrong key");
        } catch (ClassNotFoundException e) {
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
