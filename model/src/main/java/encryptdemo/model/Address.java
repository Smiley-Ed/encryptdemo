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

}
