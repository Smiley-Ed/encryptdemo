package encryptdemo.crypt;

import java.util.*;
import junit.framework.TestCase;

public class AESEncypterTest extends TestCase {

    private static Random rand = new Random();

    public void testCrypt() throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        for (int i=0; i<5; i++){
            byte[] data = new byte[i * 10];
            rand.nextBytes(data);
            rand.nextBytes(key);
            rand.nextBytes(iv);
            String s = new String(data);
            AESEncypter aes = new AESEncypter(key,iv);
            String en = aes.crypt(s);
            assertNotNull(en);
            assertNotSame(s, en);
            assertFalse(s.equals(en));
        }

    }

    public void testDecrypt() throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        for (int i=0; i<5; i++){
            byte[] data = new byte[i * 10];
            rand.nextBytes(data);
            rand.nextBytes(key);
            rand.nextBytes(iv);
            String s = new String(data);
            AESEncypter aes = new AESEncypter(key,iv);
            String en = aes.crypt(s);
            String de = aes.decrypt(en);
            assertNotNull(en);
            assertFalse (en.equals(de));
        }

    }

    public void testCryptInvalid() throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        rand.nextBytes(key);
        rand.nextBytes(iv);
        AESEncypter aes = new AESEncypter(key,iv);
        boolean fail = true;
        try {
            String en = aes.crypt(null);
        } catch (Exception e){
//            System.out.println(e);
            fail = false;
        }
        if (fail) fail("should throw an exception if null");
    }
    public void testDecryptInvalid() throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        rand.nextBytes(key);
        rand.nextBytes(iv);
        AESEncypter aes = new AESEncypter(key,iv);

        String[] invalid = {null, "XX", "XXX"};// Null input buffer, IllegalBlockSizeException
        for (String s : invalid){
            boolean fail = true;
            try {
                String en = aes.decrypt(s);
            } catch (Exception e){
//                System.out.println(e);
                fail = false;
            }
            if (fail) fail("should throw an exception if Null input buffer, IllegalBlockSizeException");
        }

    }

    public void testCryptDecrypt() throws Exception {
        byte[] key = new byte[16];
        byte[] iv = new byte[16];
        for (int i=1; i<5; i++){
            byte[] data = new byte[i * 10 + 1];
            rand.nextBytes(data);
            rand.nextBytes(key);
            rand.nextBytes(iv);
            String s = new String(data);
            AESEncypter aes = new AESEncypter(key,iv);
            String en = aes.crypt(s);
            String de = aes.decrypt(en);
            assertNotNull(de);
            assertEquals(s, de);
        }
    }
}