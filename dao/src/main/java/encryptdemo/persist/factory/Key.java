package encryptdemo.persist.factory;

import java.io.*;

/**
 * Created by esmiley on 8/11/15.
 */
public class Key implements Serializable {
    private String keyString;
    public Key(){}

    public Key(String keyString){
        this.keyString = keyString;
    }

    public String getKeyString() {
        return keyString;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }
}
