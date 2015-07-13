package encryptdemo.persist;

import java.nio.*;
import java.security.*;
import java.util.*;

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
    private byte[] iv;
    private static final long[] seedingArray = {-3811693139333175852L,
        6924023134732403504L, 3172965400344830343L, -8802874021872668068L};

    public SeedGenerator(long seed){
        this.seed = seed;

        // overflow is fine, don't worry, be happy!
        addressKey = seed * seedingArray[0];
        infoKey = seed * seedingArray[1];
        nameKey = seed * seedingArray[2];
        iv = ByteBuffer.allocate(16).putLong(seed * seedingArray[3]).array();
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

    @Override
    public String toString() {
        return "SeedGenerator{" +
                "seed=" + seed +
                ", addressKey=" + addressKey +
                ", infoKey=" + infoKey +
                ", nameKey=" + nameKey +
                ", iv=" + Arrays.toString(iv) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SeedGenerator that = (SeedGenerator) o;

        if (addressKey != that.addressKey) return false;
        if (infoKey != that.infoKey) return false;
        if (nameKey != that.nameKey) return false;
        if (seed != that.seed) return false;
        if (!Arrays.equals(iv, that.iv)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (seed ^ (seed >>> 32));
        result = 31 * result + (int) (addressKey ^ (addressKey >>> 32));
        result = 31 * result + (int) (infoKey ^ (infoKey >>> 32));
        result = 31 * result + (int) (nameKey ^ (nameKey >>> 32));
        result = 31 * result + (iv != null ? Arrays.hashCode(iv) : 0);
        return result;
    }
}
