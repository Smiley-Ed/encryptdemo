package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Created by esmiley on 7/12/15.
 */

@XmlRootElement(name = "Status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    private long millis;
    private boolean successful;
    private String message;
    public Status(){}

    public Status(long millis, boolean successful, String message){
        this.millis = millis;
        this.successful=successful;
        this.message = message;
    }

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
