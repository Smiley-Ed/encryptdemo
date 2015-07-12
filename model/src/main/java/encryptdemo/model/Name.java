package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Models the credit/charge card account owner name.
 * Created by esmiley on 7/12/15.
 */
@XmlRootElement(name = "Name")
@XmlAccessorType(XmlAccessType.FIELD)
public class Name {
    private String firstName;
    private String lastName;

    public Name(){} // streaming

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstname(){
        return firstName;
    }

    public String getLastname(){
        return lastName;
    }

    public void setFirstname(String firstName){
        this.firstName = firstName;
    }

    public void setLastname(String lastName){
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return String.format("Name{%s %s}", firstName, lastName);
    }

}
