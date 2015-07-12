package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Wrapper class for credit/charge card account.
 * Created by esmiley on 7/12/15.
 */
@XmlRootElement(name = "Card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {
    private long seed;
    private Info info;
    private Name name;
    private Address address;

    public Card(){} // streaming

    public Card( long seed, Info info, Name name, Address address){
        this.seed = seed;
        this.info = info;
        this.name = name;
        this.address = address;
    }

    public long getSeed(){
        return seed;
    }

    public Info getInfo(){
        return info;
    }

    public Name getName(){
        return name;
    }

    public Address getAddress(){
        return address;
    }

    public void setSeed(long seed){
        this.seed = seed;
    }

    public void setInfo(Info info){
        this.info = info;
    }

    public void setName(Name name){
        this.name = name;
    }

    public void setAddress(Address address){
        this.address = address;
    }

}
