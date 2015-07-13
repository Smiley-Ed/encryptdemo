package encryptdemo.persist;

/**
 * This is a very simplified exception, to keep the code simple.
 * In a production application, I'd have a more developed exception hierarchy, and force handling at some point.
 *
 * Created by esmiley on 7/12/15.
 */
public class PersistException extends RuntimeException{
    private String message;
    public PersistException(String message){
        this.message = message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
