package unam.mx.tarea03a;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encoder {

    private static final String salt = "ASYNC22nL";
    public Encoder() {
    }

    public static String encode(String toEncode){
        String encoded;
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException e){
            return  null;
        }
        messageDigest.update((toEncode + salt).getBytes(StandardCharsets.UTF_8));
        encoded = new String(messageDigest.digest());

        return encoded;
    }
}
