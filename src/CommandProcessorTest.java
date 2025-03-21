import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class. Test each possible command on
 * its bounds, if applicable to ensure they work properly.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-22-2025
 */
public class CommandProcessorTest extends TestCase {

    // CommandProcessor object for test methods
    private CommandProcessor cmdProc;

    /**
     * The setUp() method will be called automatically before each test and
     * reset whatever the test modified. For this test class, only a new
     * database object is needed, so create a database here for use in each test
     * case.
     */
    public void setUp() {
        cmdProc = new CommandProcessor();
    }


    /**
     * Tests the insert branch of the processor() method.
     */
    public void testInsert() {
        cmdProc.processor("insert ( 1 1");
        assertEquals(systemOut().getHistory(), "Point rejected: ((, 1, 1)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p -1 1");
        assertEquals(systemOut().getHistory(), "Point rejected: (p, -1, 1)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1 -1");
        assertEquals(systemOut().getHistory(), "Point rejected: (p, 1, -1)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1024 1");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, 1024, 1)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1 1024");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, 1, 1024)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1 1");
        assertEquals(systemOut().getHistory(), "Point inserted: (p, 1, 1)\n");
        systemOut().clearHistory();
        
        cmdProc.processor("insert p 2 4");
        assertEquals(systemOut().getHistory(), "Point inserted: (p, 2, 4)\n");
 
    }


    /**
     * Tests the remove by name branch of the processor() method.
     */
    public void testRemoveByName() {
        cmdProc.processor("insert p 1 1");
        
        systemOut().clearHistory();
        cmdProc.processor("remove p2");
        assertEquals(systemOut().getHistory(), "Point not removed: p2\n");
        
        systemOut().clearHistory();
        cmdProc.processor("remove p");
        //assertEquals(systemOut().getHistory(), "Removed point: (p, 1, 1)\n");
        
        
        cmdProc.processor("dump");
    }


    /**
     * Tests the remove by coordinates branch of the processor() method.
     */
    public void testRemoveByCoords() {
        cmdProc.processor("insert p1 1 1");

        cmdProc.processor("insert p2 2 2");
        cmdProc.processor("insert p3 3 3");
        
        systemOut().clearHistory();
        cmdProc.processor("remove 1 1");
        assertEquals(systemOut().getHistory(), "Point removed: (p1, 1, 1)\n");
        
        cmdProc.processor("dump");
        
        systemOut().clearHistory();
        cmdProc.processor("remove 43 2");
        assertEquals(systemOut().getHistory(), "Point not found: (43, 2)\n");
        
        cmdProc.processor("remove 3 3");
        cmdProc.processor("dump");

    }


    /**
     * Tests the regionsearch branch of the processor() method.
     */
    public void testRegionSearch() {
        

        // regionsearch 5    5   4   -2
        
        cmdProc.processor("insert p_p -1 -20");
        cmdProc.processor("insert poi 7 -8");
        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 20 20");
        assertEquals(systemOut().getHistory(), "Points intersecting region (-5, -5, 20, 20):\n"
            + "1 quadtree nodes visited\n");
    }


    /**
     * Tests the duplicates branch of the processor() method.
     */
    public void testDuplicates() {
        cmdProc.processor("duplicates");
        assertEquals(systemOut().getHistory(), "Duplicates\n");
    }


    /**
     * Tests the search branch of the processor() method.
     */
    public void testSearch() {
        cmdProc.processor("search p");
        assertEquals(systemOut().getHistory(), "Point not found: p\n");
        systemOut().clearHistory();
        
        cmdProc.processor("insert p 1 1");
        cmdProc.processor("insert p 2 4");
        systemOut().clearHistory();
        
        cmdProc.processor("search p");
        assertEquals(systemOut().getHistory(), "Found (p, 2, 4)\nFound (p, 1, 1"
            + ")\n");
    }


    /**
     * Tests the dump branch of the processor() method.
     */
    public void testDump() {
        cmdProc.processor("dump");
        assertEquals(systemOut().getHistory(), "SkipList dump:\nNode has depth "
            + "1, Value (null)\nSkipList size is: 0\nQuadTree dump:\nNode at 0,"
            + " 0, 1024: Empty\n1 quadtree nodes printed\n");
        
        TestableRandom.setNextBooleans(false);
        cmdProc.processor("insert p_p 1 20");
        
        TestableRandom.setNextBooleans(false);
        cmdProc.processor("insert poi 10 30");
        TestableRandom.setNextBooleans(true, false);
        cmdProc.processor("insert p_42 1 20");
        TestableRandom.setNextBooleans(true, true, true, false);
        cmdProc.processor("insert far 200 200");
        systemOut().clearHistory();
        cmdProc.processor("dump");
        assertFuzzyEquals(systemOut().getHistory(), "SkipList dump:\n"
            + "Node has depth 4, Value (null)\n"
            + "Node has depth 4, Value (far, 200, 200)\n"
            + "Node has depth 2, Value (p_42, 1, 20)\n"
            + "Node has depth 1, Value (p_p, 1, 20)\n"
            + "Node has depth 1, Value (poi, 10, 30)\n"
            + "SkipList size is: 4\n"
            + "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n"
            + "      Node at 0, 0, 128:\n"
            + "      (p_p, 1, 20)\n"
            + "      (poi, 10, 30)\n"
            + "      (p_42, 1, 20)\n"
            + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128:\n"
            + "      (far, 200, 200)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n"
            + "  Node at 512, 0, 512: Empty\n"
            + "  Node at 0, 512, 512: Empty\n"
            + "  Node at 512, 512, 512: Empty\n"
            + "13 quadtree nodes printed");
        
     
    }
}
