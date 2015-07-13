package encryptdemo.crypt;

import java.util.*;

/**
 * Non-safe STUB "encryption" for TESTING purposes.
 * DOES NOTHING, use to test dependency injection of encryption type.
 *
 * Created by esmiley on 7/12/15.
 */
public class StubEncrypter implements Encrypter{
    @Override
    public String crypt(String plaintext) {
        //System.out.println("NOT ENCRYPTED FOR DEMONSTRATION");
        return plaintext;
    }

    @Override
    public String decrypt(String encryptedtext) {
        //System.out.println("NOT DECRYPTED FOR DEMONSTRATION");
        return encryptedtext;
    }



}
