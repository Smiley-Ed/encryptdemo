package encryptdemo.persist;

import java.lang.reflect.*;
import java.util.*;

import encryptdemo.crypt.*;
import encryptdemo.model.Address;
import org.apache.commons.beanutils.BeanUtils;

/**
 * This is a utility class that converts a bean into an encrypted string, and, that populates an empty instance of that bean
 * with the encrypted data.
 *
 * Note that this is limited to properties that won't drop in char zeros (u0000) into their String representation.
 * Fortunately this won't be the case with the applications for this.
 *
 * Created by esmiley on 7/12/15.
 */
public class BeanCrypt<T>{
    public String encrypt (T o, Encrypter crypter) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
            Map map = BeanUtils.describe(o);
            Iterator it = map.keySet().iterator();
            while (it.hasNext()){
                Object key = it.next();
                sb.append(key);
                sb.append('\u0000');
                sb.append(map.get(key));
                sb.append('\u0000');
            }
        return crypter.crypt(sb.toString());
    }

    public void decrypt(T t, String s, Encrypter crypter) throws InvocationTargetException, IllegalAccessException{
        String data = crypter.decrypt(s);
        Map<String, String> map = new HashMap<>();
        String[] a = data.split("\u0000");
        for (int i =0; i<a.length-1; i +=2){
            map.put(a[i], a[i+1]);
        }
        BeanUtils.populate(t, map);
    }

    /**
     * This is an example of usage.
     * @param args
     */
    public static void main (String[] args){
        BeanCrypt<Address> bc = new BeanCrypt<>();
        Address address = new Address("x1", "x2", "x3", "x4");
        try {
            // normally we'd be getting some of this from somewhere else
            // ...get some bogus values to demonstrate with...
            byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            AESEncypter a = new AESEncypter(key, iv);
            String s = bc.encrypt(address, a);
            System.out.println("crypted address=" + s);
            Address a2 = new Address();
            bc.decrypt(a2,s,a);
            System.out.println( "decrypted address=" + a2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
