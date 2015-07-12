package encryptdemo.validation;

import encryptdemo.model.*;
import junit.framework.TestCase;

/**
 * Created by esmiley on 7/12/15.
 */
public class PreValidatorTest extends TestCase {

    public void testLuhnTest(){
        String [] validTestNums = {"49927398716", "1234567812345670","4111111111111111"};
        String [] invalidTestNums ={"49927398717", "1234567812345678", "4111111111111112", "", "x",null};
        for (String s : validTestNums){
            assertTrue(PreValidator.luhnTest(s));
        }
        for (String s : invalidTestNums){
            assertFalse(PreValidator.luhnTest(s));
        }
    }

    public void testCheckInfo(){
        Info info = new Info("4111111111111111", "987", "201812", "VISA");
        assertTrue(PreValidator.checkInfo(info));
        info.setType("MOXIE");
        assertFalse(PreValidator.checkInfo(info));
    }

    public void testCheckName(){ //TODO
        Name good = new Name("first", "last");
        assertTrue(PreValidator.checkName(good));
        Name[] badNames = {
                new Name("", "last"),
                new Name("first", ""),
                new Name("", ""),
                new Name(null, "last"),
                new Name("first", null),
                new Name(null, null)
        };
        for (Name name : badNames){
            assertFalse(PreValidator.checkName(name));
        }
    }

    public void testCheckAddress(){
        assertTrue(PreValidator.checkAddress(new Address("123 Love Way", "Los Amor", "CA", "95123")));
        Address[] badAddresses = {
                new Address("123 Love Way", "Los Amor", "CA", ""),
                new Address("123 Love Way", "Los Amor", "CA", null),
                new Address("123 Love Way", "Los Amor", "", "95123"),
                new Address("123 Love Way", "Los Amor", null, "95123"),
                new Address("123 Love Way", "", "CA", "95123"),
                new Address("123 Love Way", null, "CA", "95123"),
                new Address("", "Los Amor", "CA", "95123"),
                new Address(null, "Los Amor", "CA", "95123"),
                new Address("", "", "", ""),
                new Address(null, null, null, null)
        };
        for (Address address : badAddresses){
            assertFalse(PreValidator.checkAddress(address));
        }
    }


    public void testisCVVValid(){
        String[] valid = {"123", "987"};
        String[] invalid = {null, "", "4111111111111111"};
        for (String s : valid){
            assertTrue(PreValidator.isCVVValid(s));
        }
        for (String s : invalid){
            assertFalse(PreValidator.isCVVValid(s));
        }
    }

    public void testisValidType(){
        String[] valid = {"VISA", "MASTERCARD"};
        String[] invalid = {null, "", "MOXIE"};
        for (String s : valid){
            assertTrue(PreValidator.isValidType(s));
        }
        for (String s : invalid){
            assertFalse(PreValidator.isValidType(s));
        }
    }

    public void testCheckExpiration(){
        String[] valid = {"201801", "201811"};
        String[] invalid = {null, "", "huh", "123", "1234567890", "199912"};
        for (String s : valid){
            assertTrue(PreValidator.checkExpiration(s));
        }
        for (String s : invalid){
            assertFalse(PreValidator.checkExpiration(s));
        }
    }
}
