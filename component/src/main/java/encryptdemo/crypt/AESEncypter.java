package encryptdemo.crypt;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

import org.apache.commons.codec.binary.Base64;

/**
 * Usage: create with 16 byte key, which is global for the application, and a 16 byte iv spec.
 * This is the default 128 bit encryption.  If you modify your policy files, you can use a 32 byte key
 * to get full 256 bit encryption.
 *
 * Created by esmiley on 7/12/15.
 */
public class AESEncypter implements Encrypter {
    private static final String SECRET_KEY_TYPE = "AES";
    private static final String CIPHER = "AES/CBC/PKCS5Padding";
    private IvParameterSpec ivspec;// = new IvParameterSpec(iv);
    private byte[] key;

    private AESEncypter(){}

    public AESEncypter(byte[] key, byte[] iv){
        this.key = key;
        ivspec = new IvParameterSpec(iv);
    }

    @Override
    /**
     * Encrypt.
     * @param plainText not null
     * @return encrypted text, based on this configuration
     */
    public String crypt(String plainText) {
        SecretKeySpec keySpec = new SecretKeySpec(key, SECRET_KEY_TYPE);
        byte[] input = plainText.getBytes();
        byte[] output;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivspec);
            output = cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
        return Base64.encodeBase64String(output);
    }

    @Override
    /**
     * Do not pass null or an illegal block size (not multiple of 16).
     * @param encryptedText not null, and size is multiple of 16
     * @return decrypted text, based on this configuration
     */
    public String decrypt(String encryptedText) {
        SecretKeySpec keySpec = new SecretKeySpec(key, SECRET_KEY_TYPE);
        byte[] input = Base64.decodeBase64(encryptedText);
        byte[] output;
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);
            output = cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
        return  new String(output);
    }

    // Usage example
//    public static void main (String[] args){
//        byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
//        //System.out.println("started, key size " + key.length);
//        byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//        AESEncypter a = new AESEncypter(key, iv);
//
//        String s = "funny puppy\u0000";
//        String crypt = a.crypt(s);
//
//        //System.out.println("crypt=>" + crypt);
//        //System.out.println("decrypt=>" + a.decrypt(crypt) + " " + a.decrypt(crypt).equals(s));
//
//
//    }
}
