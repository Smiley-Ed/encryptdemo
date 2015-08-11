package encryptdemo.persist;

import java.util.*;
import java.util.concurrent.*;

/**
 * This is a stub implementation for testing and demonstration purposes.
 *
 * It creates IN MEMORY persistence, and DOES NOT really persist anything.
 * The insert method is implemented as insert-or-update, as it does not check if the record exists.
 * These would be semantically distinct in many persistence implementations.
 *
 * Created by esmiley on 7/13/15.
 */
public class CardDaoTestImpl extends CardDao {
    static final Map<Long, String> internalMap = new ConcurrentHashMap<>();

    @Override
    public String select(long key) {
        //System.out.println("select " +internalMap.keySet());
        //System.out.println("select key " +key);
        return internalMap.get(key);
    }

    @Override
    public void insert(long key, String data) {
        //System.out.println("insert " +internalMap.keySet());
        //System.out.println("insert key " +key);
        internalMap.put(key, data);
    }

    @Override
    public void update(long key, String data) {
        internalMap.put(key, data);
    }

    @Override
    public void delete(long key) {
        internalMap.remove(key);
    }
}
