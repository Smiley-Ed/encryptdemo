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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        if (firstName != null ? !firstName.equals(name.firstName) : name.firstName != null) return false;
        if (lastName != null ? !lastName.equals(name.lastName) : name.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
