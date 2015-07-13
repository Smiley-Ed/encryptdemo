package encryptdemo.persist;

import encryptdemo.crypt.AESEncypter;
import encryptdemo.model.*;

/**
 * This has the main functionality but is calling a abstract persistence primitives.
 * The implemented methods create encrypted data for our objects and primary keys to use in calling the primitives.
 * You can see how the records are split, so that even having some of the data decrypted will not tell you which names,
 * addresses, and locations go together.
 *
 *
 * Created by esmiley on 7/12/15.
 */
public abstract class CardDao {

    public void create(Card card){
        SeedGenerator gen = new SeedGenerator(card.getSeed());
        byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };//TODO we need to add a factory and IOC here
        try{
            AESEncypter aes = new AESEncypter(key, gen.getIv());

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

    public Card read(long seed){
        SeedGenerator gen = new SeedGenerator(seed);
        byte[] aeskey = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };//TODO we need to add a factory and IOC here
        try{
            AESEncypter aes = new AESEncypter(aeskey, gen.getIv());

            BeanCrypt<Address> bca = new BeanCrypt<>();
            String cryptAddr = select(gen.getAddressKey());
            Address address = new Address();
            bca.decrypt(address, cryptAddr, aes);


            BeanCrypt<Info> bci = new BeanCrypt<>();
            String cryptInfo = select(gen.getInfoKey());
            Info info = new Info();
            bci.decrypt(info, cryptInfo, aes);


            BeanCrypt<Name> bcn = new BeanCrypt<>();
            String cryptName = select(gen.getNameKey());
            Name name = new Name();
            bcn.decrypt(name, cryptName, aes);

            Card card = new Card(seed, info, name, address);
            return card;

        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    public void modify(Card card){
        SeedGenerator gen = new SeedGenerator(card.getSeed());
        byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };//TODO we need to add a factory and IOC here
        try{
            AESEncypter aes = new AESEncypter(key, gen.getIv());

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

    public void remove(long seed){
        SeedGenerator gen = new SeedGenerator(seed);
        byte[] key = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };//TODO we need to add a factory and IOC here
        try{
            AESEncypter aes = new AESEncypter(key, gen.getIv());
            delete(gen.getAddressKey());
            delete(gen.getInfoKey());
            delete(gen.getNameKey());
        } catch (Exception e){
            throw new PersistException(e.getMessage().toString());
        }
    }

    // implement these primitives in any implementation
    public abstract String select(long key);
    public abstract void insert(long key, String data);
    public abstract void update(long key, String data);
    public abstract void delete(long key);

}
