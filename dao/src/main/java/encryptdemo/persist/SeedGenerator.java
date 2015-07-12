package encryptdemo.persist;

import java.nio.*;
import java.security.*;

/**
 * This generates primary keys for the three types of data.
 * These are longs, and always seeded by the same seed for the same card data object.
 * Created by esmiley on 7/12/15.
 */
public class SeedGenerator {
    private long seed;
    private long addressKey;
    private long infoKey;
    private long nameKey;
    private byte[] iv = new byte[16];

    public SeedGenerator(long seed){
        this.seed = seed;
        byte[] bytes = ByteBuffer.allocate(8).putLong(seed).array();
        SecureRandom rand = new SecureRandom(bytes);
        addressKey = rand.nextLong();
        infoKey = rand.nextLong();
        nameKey = rand.nextLong();
        rand.nextBytes(iv);
    }

    public long getAddressKey(){
        return addressKey;
    };

    public long getInfoKey(){
        return infoKey;
    };

    public long getNameKey(){
        return nameKey;
    };

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }
}
