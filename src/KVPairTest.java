import student.TestCase;

/**
 * This class tests the KVPair class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Martin Hamzai and Richmond Southall
 * 
 * @version 02-25-2025
 */
public class KVPairTest extends TestCase {

    // KVPair objects to be used for the tests
    private KVPair<String, Integer> pair;
    private KVPair<String, Integer> pair2;

    /**
     * Initializes a KVPair object to be used for the tests.
     */
    public void setUp() {
        pair = new KVPair<>("key", 123);
        pair2 = new KVPair<>("key", 123);
    }
    
    /**
     * Tests both versions of the compareTo() method.
     */
    public void testCompareTo() {
        assertTrue(pair.compareTo(pair2) == 0);
        assertTrue(pair.compareTo(pair2.key()) == 0);
    }


    /**
     * Tests the key() method by asserting the returned key
     * is equal to the expected value.
     */
    public void testKey() {
        assertEquals(pair.key(), "key");
    }


    /**
     * Tests the value() method by asserting the returned value
     * is equal to the expected value.
     */
    public void testvalue() {
        assertTrue(pair.value() == 123);
    }


    /**
     * Tests the toString() method by asserting the returned string
     * representation is equal to the expected value.
     */
    public void testToString() {
        String testStr = "key, 123";
        assertEquals(pair.toString(), testStr);
    }

}
