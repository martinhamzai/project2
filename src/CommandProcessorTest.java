import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class. Test each possible command on
 * its bounds, if applicable to ensure they work properly.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 02-25-2025
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
    }


    /**
     * Tests the remove by name branch of the processor() method.
     */
    public void testRemoveByName() {
        cmdProc.processor("remove p");
        assertEquals(systemOut().getHistory(), "Remove by name\n");
    }


    /**
     * Tests the remove by coordinates branch of the processor() method.
     */
    public void testRemoveByCoords() {
        cmdProc.processor("remove 1 1");
        assertEquals(systemOut().getHistory(), "Remove by coords\n");
    }


    /**
     * Tests the regionsearch branch of the processor() method.
     */
    public void testRegionSearch() {
        cmdProc.processor("regionsearch 1 1 1 1");
        assertEquals(systemOut().getHistory(), "Regionsearch\n");
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
        assertEquals(systemOut().getHistory(), "SkipList dump:\n"
            + "Node with depth 1, value null\n" + "SkipList size is: 0\n");
    }
}
