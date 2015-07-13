package encryptdemo.persist;

import junit.framework.TestCase;

/**
 * Created by esmiley on 7/13/15.
 */
public class SeedGeneratorTest extends TestCase {
    public void testConsistent(){
        long[] tests = {
                5015858238834291257L,
                1562905954316958733L,
                5954311562906958733L,
                3883425015858291257L,
                -3528459280989053346L,
                -6251126491121074784L,
                0
        };
        for (long test : tests){
            SeedGenerator sg1 = new SeedGenerator(test);
            SeedGenerator sg2 = new SeedGenerator(test);
            assertEquals(sg1, sg2);

        }
    }

}
