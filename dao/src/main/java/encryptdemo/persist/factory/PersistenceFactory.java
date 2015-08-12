package encryptdemo.persist.factory;

import javax.ws.rs.HEAD;

import encryptdemo.crypt.*;
import encryptdemo.persist.*;

/**
 * This is a wrapper for IoC strategy to insulate application from implementation details
 * Created by esmiley on 7/13/15.
 */
public class PersistenceFactory {
    private PersistenceFactory(){}

    public static Encrypter getEncrypter(byte[] iv){ //iv is different for each persistence instance
        byte[] key =  KeyHelper.create(SpringFactoryHelper.getKey().getKeyString());
//
//        byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };//
//
        return new AESEncypter(key, iv);
    }

    private static CardDao daoInstance = new CardDaoTestImpl(); // todo IOC

    public static CardDao getCardDao(){
        return daoInstance;
    }

}
