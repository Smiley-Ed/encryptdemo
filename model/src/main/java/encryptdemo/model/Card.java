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

    @Override
    public String toString() {
        return "Card{" +
                "seed=" + seed +
                ", info=" + info +
                ", name=" + name +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (seed != card.seed) return false;
        if (address != null ? !address.equals(card.address) : card.address != null) return false;
        if (info != null ? !info.equals(card.info) : card.info != null) return false;
        if (name != null ? !name.equals(card.name) : card.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (seed ^ (seed >>> 32));
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
