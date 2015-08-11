package encryptdemo.persist.factory;

/**
 * Created by esmiley on 7/29/15.
 */
public class KeyHelper {
    private static final int BYTES_IN_KEY = 16; // this is for 128 bit encryption, use 32 for 256 bit
    private KeyHelper(){}

    public byte[] create(String keyString){
        byte[] b = new byte[BYTES_IN_KEY];
        if (keyString==null || keyString.isEmpty()){
            throw new IllegalArgumentException("unable to generate for empty or null string");
        }
        byte[] bytes = keyString.getBytes();
        if (bytes.length<BYTES_IN_KEY){
            throw new IllegalArgumentException("string is too short to use for generation");
        }
        System.arraycopy(bytes, 0, b, 0, BYTES_IN_KEY);
        return b;
    }
}
