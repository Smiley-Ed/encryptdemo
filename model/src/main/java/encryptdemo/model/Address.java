package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Models the credit/charge card account billing address information.
 * Created by esmiley on 7/12/15.
 */
@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    private String address;
    private String city;
    private String stateOrProvince;
    private String zipOrPostalCode;

    public Address(){} // streaming
    public Address(String address, String city, String stateOrProvince,String zipOrPostalCode){
        this.address = address;
        this.city = city;
        this.stateOrProvince = stateOrProvince;
        this.zipOrPostalCode = zipOrPostalCode;
    }

    public String getAddress(){
        return address;
    }

    public String getCity(){
        return city;
    }

    public String getStateOrProvince(){
        return stateOrProvince;
    }

    public String getZipOrPostalcode(){
        return zipOrPostalCode;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setStateOrProvince(String stateOrProvince){
        this.stateOrProvince = stateOrProvince;
    }

    public void setZipOrPostalcode(String zipOrPostalCode){
        this.zipOrPostalCode = zipOrPostalCode;
    }

    @Override
    public String toString(){
        return "Address{" + address +"," + city + "," + stateOrProvince + ", " + zipOrPostalCode + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address1 = (Address) o;

        if (address != null ? !address.equals(address1.address) : address1.address != null) return false;
        if (city != null ? !city.equals(address1.city) : address1.city != null) return false;
        if (stateOrProvince != null ? !stateOrProvince.equals(address1.stateOrProvince) : address1.stateOrProvince != null) {
            return false;
        }
        if (zipOrPostalCode != null ? !zipOrPostalCode.equals(address1.zipOrPostalCode) : address1.zipOrPostalCode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (stateOrProvince != null ? stateOrProvince.hashCode() : 0);
        result = 31 * result + (zipOrPostalCode != null ? zipOrPostalCode.hashCode() : 0);
        return result;
    }
}
