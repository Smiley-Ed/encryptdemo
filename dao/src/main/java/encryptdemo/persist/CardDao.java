package encryptdemo.persist;

import encryptdemo.crypt.*;
import encryptdemo.model.*;
import encryptdemo.persist.factory.PersistenceFactory;

/**
 * This has the main functionality but is calling a abstract persistence primitives.
 * The implemented methods create encrypted data for our objects and primary keys to use in calling the primitives.
 * You can see how the records are split, so that even having some of the data decrypted will not tell you which names,
 * addresses, and credit card numbers go together.  This would be bad news for would be cracker, who has gotten DB access,
 * and somehow managed to attack encryption successfully.
 *
 *
 * Created by esmiley on 7/12/15.
 */
public abstract class CardDao {

    public void create(Card card) throws PersistException{
        SeedGenerator gen = new SeedGenerator(card.getSeed());
        try{
            Encrypter aes = PersistenceFactory.getEncrypter( gen.getIv());

            BeanCrypt<Address> bca = new BeanCrypt<>();
            String cryptAddr = bca.encrypt(card.getAddress(), aes);
            insert(gen.getAddressKey(), cryptAddr);

            BeanCrypt<Info> bci = new BeanCrypt<>();
            String cryptInfo = bci.encrypt(card.getInfo(), aes);
            insert(gen.getInfoKey(), cryptInfo);

            BeanCrypt<Name> bcn = new BeanCrypt<>();
            String cryptName = bcn.encrypt(card.getName(), aes);
            insert(gen.getNameKey(), cryptName);
        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    public Card read(long seed) throws PersistException{
        try{
            SeedGenerator gen = new SeedGenerator(seed);
            Encrypter aes = PersistenceFactory.getEncrypter( gen.getIv());

            BeanCrypt<Address> bca = new BeanCrypt<>();
            String cryptAddr = select(gen.getAddressKey());
            Address address = new Address();
            bca.decrypt(address, cryptAddr, aes);
            //System.out.println("DEBUG address " + address);


            BeanCrypt<Info> bci = new BeanCrypt<>();
            String cryptInfo = select(gen.getInfoKey());
            Info info = new Info();
            bci.decrypt(info, cryptInfo, aes);
            //System.out.println("DEBUG info " + info);


            BeanCrypt<Name> bcn = new BeanCrypt<>();
            String cryptName = select(gen.getNameKey());
            Name name = new Name();
            bcn.decrypt(name, cryptName, aes);
            //System.out.println("DEBUG name " + name);

            Card card = new Card(seed, info, name, address);
            return card;

        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    public void modify(Card card) throws PersistException{
        try{
            SeedGenerator gen = new SeedGenerator(card.getSeed());
            Encrypter aes = PersistenceFactory.getEncrypter( gen.getIv());

            BeanCrypt<Address> bca = new BeanCrypt<>();
            String cryptAddr = bca.encrypt(card.getAddress(), aes);
            update(gen.getAddressKey(), cryptAddr);

            BeanCrypt<Info> bci = new BeanCrypt<>();
            String cryptInfo = bci.encrypt(card.getInfo(), aes);
            update(gen.getInfoKey(), cryptInfo);

            BeanCrypt<Name> bcn = new BeanCrypt<>();
            String cryptName = bcn.encrypt(card.getName(), aes);
            update(gen.getNameKey(), cryptName);
        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    public void remove(long seed) throws PersistException{
        try{
            SeedGenerator gen = new SeedGenerator(seed);
            Encrypter aes = PersistenceFactory.getEncrypter( gen.getIv());
            delete(gen.getAddressKey());
            delete(gen.getInfoKey());
            delete(gen.getNameKey());
        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    // implement these primitives in any persistence implementation
    // e.g. in an RDBMS, something in the form
    //  SELECT DATA FROM ACCTDATA WHERE ID={key}
    //  INSERT INTO ACCTDATA ID,DATA VALUES({key}, {data})
    //  UPDATE ACCTDATA SET DATA={data} WHERE ID={key}
    //  DELETE FROM ACCTDATA WHERE ID={key}
    public abstract String select(long key);
    public abstract void insert(long key, String data);
    public abstract void update(long key, String data);
    public abstract void delete(long key);

}
