import java.util.Iterator;
import java.util.ArrayList;

import org.junit.Test;

import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the methods of SkipList class
 * 
 * @author Martin Hamzai and Richmond Southall
 * 
 * @version 2025-02-25
 */

public class SkipListTest extends TestCase {

    // Declared SkipList object to use in test methods
    private SkipList<String, Integer> sl;

    // integer names, objects, and key value pairs to use in test methods
    private String int1Name;
    private Integer int1;
    private KVPair<String, Integer> kv1;

    private String int2Name;
    private Integer int2;
    private KVPair<String, Integer> kv2;

    private String int3Name;
    private Integer int3;
    private KVPair<String, Integer> kv3;

    /**
     * Initializes the SkipList object for test methods
     */
    public void setUp() {
        sl = new SkipList<String, Integer>();

        int1Name = "int1";
        int1 = 1;
        kv1 = new KVPair<String, Integer>(int1Name, int1);

        int2Name = "int2";
        int2 = 2;
        kv2 = new KVPair<String, Integer>(int2Name, int2);

        int3Name = "int";
        int3 = 2;
        kv3 = new KVPair<String, Integer>(int3Name, int3);

    }


    /***
     * Example 1: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    public void testRandomLevelOne() {
        TestableRandom.setNextBooleans(false);
        int randomLevelValue = sl.randomLevel();

        // This returns 1 because the first preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 1; // 1

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    /***
     * Example 2: Test `randomLevel` method with
     * predetermined random values using `TestableRandom`
     */
    public void testRandomLevelFour() {
        TestableRandom.setNextBooleans(true, true, true, false, true, false);
        int randomLevelValue = sl.randomLevel();

        // This returns 4 because the fourth preset
        // random boolean is `false` which breaks
        // the `while condition inside the `randomLevel` method
        int expectedLevelValue = 4; // 4

        // Compare the values
        assertEquals(expectedLevelValue, randomLevelValue);
    }


    /**
     * Tests the search() method for the SkipList.
     */
    public void testSearch() {
        // test empty list
        ArrayList<KVPair<String, Integer>> pairs = sl.search(kv2.key());
        assertTrue(pairs.size() == 0);

        sl.insert(kv1);
        sl.insert(kv2);
        sl.insert(kv3);
        sl.insert(kv2);
        sl.insert(kv1);

        pairs = sl.search(kv3.key());
        KVPair<String, Integer> pair = pairs.get(0);
        assertEquals(pair.key(), kv3.key());
        assertTrue(pair.value().equals(kv3.value()));

        // test search with two pairs with same key
        pairs = sl.search(kv2.key());
        KVPair<String, Integer> pair1 = pairs.get(0);
        assertEquals(pair1.key(), kv2.key());
        assertTrue(pair1.value().equals(kv2.value()));
        KVPair<String, Integer> pair2 = pairs.get(0);
        assertEquals(pair2.key(), kv2.key());
        assertTrue(pair2.value().equals(kv2.value()));

        // test search with key that is not present
        pairs = sl.search("int4");
        assertTrue(pairs.size() == 0);

    }


    /**
     * Tests the size() method for the SkipList.
     */
    public void testSize() {
        assertTrue(sl.size() == 0);
        sl.insert(kv1);
        assertTrue(sl.size() == 1);
    }


    /**
     * Tests the insert() method for the SkipList.
     */
    public void testInsert() {
        // depth 1
        TestableRandom.setNextBooleans(false);
        sl.insert(kv1);
        sl.dump();
        assertEquals(
            "SkipList dump:\nNode with depth 1, value null\nNode with depth 1,"
                + " value int1, 1\nSkipList size is" + ": 1\n", systemOut()
                    .getHistory());
        systemOut().clearHistory();

        // depth 2, ensure after kv1 and head level changes
        TestableRandom.setNextBooleans(true, false);
        sl.insert(kv2);
        sl.dump();
        assertEquals(
            "SkipList dump:\nNode with depth 2, value null\nNode with depth 1,"
                + " value int1, 1\nNode with depth 2, value int2, 2\nSkipList "
                + "size is" + ": 2\n", systemOut().getHistory());
        systemOut().clearHistory();

        // depth 2, ensure before kv1 and head level changes
        TestableRandom.setNextBooleans(true, false);
        sl.insert(kv3);
        sl.dump();
        assertEquals(
            "SkipList dump:\nNode with depth 2, value null\nNode with depth 2, "
                + "value int, 2\nNode with depth 1, value int1, 1\nNode with de"
                + "pth 2, value int2, 2\nSkipList size is: 3\n", systemOut()
                    .getHistory());
        systemOut().clearHistory();

        // depth 3, ensure head changes and kv is placed before node with same
        // name
        TestableRandom.setNextBooleans(true, true, false);
        sl.insert(kv1);
        sl.dump();
        assertEquals(
            "SkipList dump:\nNode with depth 3, value null\nNode with depth 2, "
                + "value int, 2\nNode with depth 3, value int1, 1\nNode with de"
                + "pth 1, value int1, 1\nNode with depth 2, value int2, 2\nSkip"
                + "List size is: 4\n", systemOut().getHistory());
    }


    /**
     * Tests the adjustHead() method for the SkipList.
     */
    public void testAdjustHead() {
        sl.dump();
        assertEquals("SkipList dump:\nNode with depth 1, value null\nSkipList s"
            + "ize is: 0\n", systemOut().getHistory());
        systemOut().clearHistory();

        sl.adjustHead(2);
        sl.dump();
        assertEquals("SkipList dump:\nNode with depth 2, value null\nSkipList s"
            + "ize is: 0\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * Tests the remove(key) method for the SkipList.
     */
    public void testRemoveKey() {
        // empty list
        assertEquals(sl.remove(kv1.key()), null);

        // one item in list
        sl.insert(kv1);
        assertEquals(sl.remove(kv1.key()), kv1);

        // three items in list, remove one at end (kv2)
        sl.insert(kv1);
        sl.insert(kv2);
        sl.insert(kv3);
        assertEquals(sl.remove(kv2.key()), kv2);

        // if does not exist in list
        assertEquals(sl.remove("int5"), null);

        sl.remove(kv1.key());
        sl.remove(kv3.key());

        TestableRandom.setNextBooleans(true, true, true, true, true, false);
        sl.insert(kv1);
        assertEquals(sl.remove(kv1.key()), kv1);
        System.out.println();
        sl.dump();
        System.out.println();
    }


    /**
     * Tests the remove(value) method for the SkipList.
     */
    public void testRemoveValue() {
        // empty list
        assertEquals(sl.removeByValue(kv1.value()), null);

        sl.insert(kv1);
        assertEquals(sl.removeByValue(kv1.value()), kv1);

        sl.insert(kv1);
        sl.insert(kv3);
        sl.dump();
        assertEquals(sl.removeByValue(kv1.value()), kv1);
        assertEquals(sl.removeByValue(kv1.value()), null);

        sl.insert(kv1);
        assertEquals(sl.removeByValue(kv3.value()), kv3);

        sl.removeByValue(kv1.value());
        TestableRandom.setNextBooleans(true, true, true, true, true, false);
        sl.insert(kv1);
        TestableRandom.setNextBooleans(true, false);
        sl.insert(kv2);
        assertEquals(sl.removeByValue(kv1.value()), kv1);
    }


    /**
     * Tests the dump() method for the SkipList.
     */
    public void testDump() {

        // no nodes
        sl.dump();
        assertEquals("SkipList dump:\nNode with depth 1, value null\nSkipList s"
            + "ize is: 0\n", systemOut().getHistory());
        systemOut().clearHistory();

        // with nodes
        TestableRandom.setNextBooleans(false);
        sl.insert(kv1);
        sl.dump();
        assertEquals(
            "SkipList dump:\nNode with depth 1, value null\nNode with depth 1,"
                + " value int1, 1\nSkipList size is" + ": 1\n",
            systemOut().getHistory());
        systemOut().clearHistory();

    }

}
