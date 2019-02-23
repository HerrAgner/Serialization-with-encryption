package Encryption;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.spec.KeySpec;

public class Encryption {

    public SealedObject encryptObject(Serializable obj){
        try {
            final char[] password = "secret_password".toCharArray();
            final byte[] salt = "random_salt".getBytes();
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(password, salt, 1024, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secret);
//            new SerializeObject().serializeObjectToFile(secret, Paths.get("networkKey.txt"));
            return new SealedObject(obj, cipher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("returning null");
        return null;
    }
}
