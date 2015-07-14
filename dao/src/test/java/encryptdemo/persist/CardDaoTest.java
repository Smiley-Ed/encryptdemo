/**
 * Created by esmiley on 7/13/15.
 */
package encryptdemo.persist;
import junit.framework.TestCase;

import encryptdemo.model.*;


public class CardDaoTest extends TestCase{
    private static CardDao mockDao = new CardDaoTestImpl();

    public void testCreateRead(){
        long seed = 123456789L;
        Address address = new Address("123 Love Way", "Los Amor", "CA", "95123");
        Info info = new Info("4111111111111111", "987", "201812", "VISA");
        Name name = new Name("first", "last");
        Card card = new Card(seed, info, name, address);
        mockDao.create(card);
        Card card2 = mockDao.read(seed);
        assert(card.getSeed()==card2.getSeed());
        assert(card.getName().equals(card2.getName()));
        assert(card.getAddress().equals(card2.getAddress()));
        assert(card.getInfo().equals(card2.getInfo()));
        assertTrue(card2.toString(), card.equals(card2));
    }

    public void testCreateModifyRead(){

    }
    public void testCreateRemove(){

    }
}
