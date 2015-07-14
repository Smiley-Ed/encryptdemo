package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Models the core credit/charge card account information
 * Created by esmiley on 7/12/15.
 */
@XmlRootElement(name = "Info")
@XmlAccessorType(XmlAccessType.FIELD)
public class Info {
    private String number;
    private String cvv;
    private String expiration;
    private String type;

    public Info(){} // streaming

    public Info(String number, String cvv, String expiration,String type){
        this.number = number;
        this.type = type;
        this.expiration = expiration;
        this.cvv = cvv;
        int len = number.length();
    }

    public String getNumber(){
        return number;
    }

    public String getCvv(){
        return cvv;
    }

    public String getExpiration(){
        return expiration;
    }

    public String getType(){
        return type;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public void setCvv(String cvv){
        this.cvv = cvv;
    }

    public void setExpiration(String expiration){
        this.expiration = expiration;
    }

    public void setType(String type){
        this.type = type;
    }

    /**
     * Very IMPORTANT. Full number suppressed!
     *
     * @return representation that DOES NOT contain the FULL CREDIT CARD number, just the type, last 4 and expiration.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Card: ");
        sb.append(getType());
        sb.append(' ');
        String last4;
        int len = number.length();
        if (len<4){
            last4 = "****";
        } else {
            last4 =  number.substring(len-4);
        }

        sb.append(last4);
        sb.append(' ');
        sb.append(expiration);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (cvv != null ? !cvv.equals(info.cvv) : info.cvv != null) return false;
        if (expiration != null ? !expiration.equals(info.expiration) : info.expiration != null) return false;
        if (number != null ? !number.equals(info.number) : info.number != null) return false;
        if (type != null ? !type.equals(info.type) : info.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (cvv != null ? cvv.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
