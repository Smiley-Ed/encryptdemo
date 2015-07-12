package encryptdemo.model;

import javax.xml.bind.annotation.*;

/**
 * Created by esmiley on 7/12/15.
 */

@XmlRootElement(name = "Status")
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {
    private long millis;

    public long getMillis() {
        return millis;
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }
}
