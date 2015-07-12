package encryptdemo.persist;

/**
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
