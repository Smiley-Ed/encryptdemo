package encryptdemo.validation;

import java.util.*;

import encryptdemo.model.*;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Utility class. Prevalidates card information.
 * Created by esmiley on 7/12/15.
 */
public class PreValidator {
    private PreValidator(){}
    public static enum CardType{ VISA, MASTERCARD, DISCOVER}//TODO move out for client use?....

    public static boolean validate(Card card){
        if (!checkInfo(card.getInfo()) ||
            !checkName(card.getName()) ||
            !checkAddress(card.getAddress())
            ){
            return false;
        }
        return true;
    }

    public static boolean checkInfo(Info info){
        if (info==null){
            return false;
        }
        if (!isCVVValid(info.getCvv()) ||
            !isValidType(info.getType())||
            !checkExpiration(info.getExpiration()) ||
            !luhnTest(info.getNumber())
            ){
            return false;
        }
        return true;
    }

    /**
     * The string is expected to be a valid date string in YYYYMM format and in this century
     * @param yyyymm
     * @return
     */
    static boolean checkExpiration(String yyyymm){
        if (yyyymm==null || yyyymm.length()<6 || !yyyymm.startsWith("20")){
            return false;
        }
        String m = yyyymm.substring(4);
        if (m.startsWith("0")){
            m = m.substring(1);
        }
        try {
            int i = Integer.valueOf(m);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    static boolean isCVVValid(String cvv){
        return cvv!=null && cvv.length()==3;
    }

    static boolean isValidType(String type) {
        for (CardType c : CardType.values()) {
            if (c.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Very superficial check for address fields.
     * This is an example.  Some validation ought to be done on the client, too.
     * Depending on our use cases we may only want to validate the zip or postal code.
     * @param address
     * @return
     */
    public static boolean checkAddress(Address address){
        if (address==null){
            return false;
        }
        String[] data = {address.getAddress(), address.getCity(), address.getStateOrProvince(), address.getZipOrPostalcode()};
        return checkIsSet(data);
    }

    /**
     * Very superficial check for name fields.
     * @param name
     * @return
     */
    public static boolean checkName(Name name){
        if (name==null){
            return false;
        }

        String[] data = {name.getFirstname(), name.getLastname()};
        return checkIsSet(data);
    }

    // helper
    private static boolean checkIsSet(String[] data){
        for (String datum : data){
            if (datum==null || datum.isEmpty()){
                return false;
            }
        }
        return true;
    }



    /**
     * Pre-check plausibility of credit card number.
     * For example, the test credit card number, "4111111111111111" is legitimately structured,
     * even though it is not associated with an actual account.
     * Reference: http://rosettacode.org/wiki/Luhn_test_of_credit_card_numbers
     * @param number a credit card number as a string
     * @return true if number is legitimately structured, this may be a test credit card number or one that has not been issued.
     */
    public static boolean luhnTest(String number){
        if (number==null || number.isEmpty()){
            return false;
        }
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

}
