package encryptdemo.crypt;

/**
 * Created by esmiley on 7/12/15.
 */
public interface Encrypter {
    public String crypt(String plaintext);
    public String decrypt(String encryptedtext);

}
