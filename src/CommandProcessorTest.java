import student.TestCase;
import student.TestableRandom;

/**
 * This class tests the CommandProcessor class. Test each possible command on
 * its bounds, if applicable to ensure they work properly. Also test passing
 * improper command to ensure all class functionalities work as intended.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 02-22-2025
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
        // valid rectangle
        cmdProc.processor("insert rec 1 1 1 1");
        assertEquals("Rectangle inserted: (rec, 1, 1, 1, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // invalid name
        cmdProc.processor("insert 1rec 1 1 1 1");
        assertEquals("Rectangle rejected: (1rec, 1, 1, 1, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // invalid x
        cmdProc.processor("insert rec 1033 1 1 1");
        assertEquals("Rectangle rejected: (rec, 1033, 1, 1, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // invalid y
        cmdProc.processor("insert rec 1 10244 1 1");
        assertEquals("Rectangle rejected: (rec, 1, 10244, 1, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // invalid width
        cmdProc.processor("insert rec 1 1 0 1");
        assertEquals("Rectangle rejected: (rec, 1, 1, 0, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        // invalid height
        cmdProc.processor("insert rec 1 1 1 0");
        assertEquals("Rectangle rejected: (rec, 1, 1, 1, 0)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        System.out.println();
    }


    /**
     * Tests the remove by name branch of the processor() method.
     */
    public void testRemoveByName() {
        cmdProc.processor("remove rec");
        assertEquals("Rectangle not removed: rec\n", systemOut().getHistory());
        cmdProc.processor("insert rec 1 1 1 1");
        systemOut().clearHistory();
        cmdProc.processor("remove rec");
        assertEquals("Rectangle removed: (rec, 1, 1, 1, 1)\n", systemOut()
            .getHistory());

        System.out.println();
    }


    /**
     * Tests the remove by coordinates branch of the processor() method.
     */
    public void testRemoveByCoords() {
        cmdProc.processor("remove -1 1 0 1");
        assertEquals("Rectangle rejected: (-1, 1, 0, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();
        cmdProc.processor("remove 1 1 1");
        assertEquals("Invalid number of arguments.\n", systemOut()
            .getHistory());

        cmdProc.processor("insert rec 1 1 1 1");
        systemOut().clearHistory();
        cmdProc.processor("remove 1 1 1 1");
        assertEquals("Rectangle removed: (rec, 1, 1, 1, 1)\n", systemOut()
            .getHistory());

        systemOut().clearHistory();
        cmdProc.processor("remove 1 1 2 1");
        assertEquals("Rectangle not found: (1, 1, 2, 1)\n", systemOut()
            .getHistory());

        System.out.println();
    }


    /**
     * Tests the regionsearch branch of the processor() method.
     */
    public void testRegionSearch() {
        cmdProc.processor("insert rec1 1 1 1 1");
        cmdProc.processor("insert rec2 1 1 2 2");
        cmdProc.processor("insert rec3 5 5 1 1");
        systemOut().clearHistory();

        cmdProc.processor("regionsearch 1 1 1 1");
        assertEquals(
            "Rectangles intersecting region (1, 1, 1, 1):\n(rec1, 1, 1, 1, 1)\n"
                + "(rec2, 1, 1, 2, 2)\n", systemOut().getHistory());
        systemOut().clearHistory();

        cmdProc.processor("regionsearch 1 1 -10 1");
        assertEquals("Rectangle rejected: (1, 1, -10, 1)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProc.processor("regionsearch 1 1 1 -20");
        assertEquals("Rectangle rejected: (1, 1, 1, -20)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProc.processor("regionsearch 1 1 -5 -20");
        assertEquals("Rectangle rejected: (1, 1, -5, -20)\n", systemOut()
            .getHistory());
        systemOut().clearHistory();

        cmdProc.processor("regionsearch -1 -1 5 5");
        assertEquals(
            "Rectangles intersecting region (-1, -1, 5, 5):\n(rec1, 1, 1, 1, 1)"
                + "\n(rec2, 1, 1, 2, 2)\n", systemOut().getHistory());
        systemOut().clearHistory();
    }


    /**
     * Tests the intersections branch of the processor() method.
     */
    public void testIntersections() {
        cmdProc.processor("insert rec1 1 1 1 1");
        cmdProc.processor("insert rec2 1 1 2 2");
        cmdProc.processor("insert rec3 5 5 1 1");
        systemOut().clearHistory();
        cmdProc.processor("intersections");
        assertEquals(
            "Intersection pairs:\n(rec1, 1, 1, 1, 1)  |  (rec2, 1, 1, 2, 2)\n",
            systemOut().getHistory());
        systemOut().clearHistory();

    }


    /**
     * Tests the search branch of the processor() method.
     */
    public void testSearch() {

        cmdProc.processor("search rec");
        assertEquals("Rectangle not found: (rec)\n", systemOut().getHistory());

        cmdProc.processor("insert rec 1 1 1 1");
        systemOut().clearHistory();
        cmdProc.processor("search rec");
        assertEquals("Rectangles found:\n(rec, 1, 1, 1, 1)\n", systemOut()
            .getHistory());

        cmdProc.processor("insert rec 2 2 2 2");
        systemOut().clearHistory();
        cmdProc.processor("search rec");
        assertEquals(
            "Rectangles found:\n(rec, 2, 2, 2, 2)\n(rec, 1, 1, 1, 1)\n",
            systemOut().getHistory());

        System.out.println();
    }


    /**
     * Tests the dump branch of the processor() method.
     */
    public void testDump() {

        // empty
        cmdProc.processor("dump");
        assertEquals(
            "SkipList dump:\nNode with depth 1, value null\nSkipList size is"
                + ": 0\n", systemOut().getHistory());

        System.out.println();

        // dump with nodes
        TestableRandom.setNextBooleans(true, true, false);
        cmdProc.processor("insert rec1 1 1 1 1");

        TestableRandom.setNextBooleans(true, false);
        cmdProc.processor("insert rec2 2 2 2 2");

        System.out.println();

        systemOut().clearHistory();

        cmdProc.processor("dump");
        assertEquals(
            "SkipList dump:\nNode with depth 3, value null\nNode with depth 3,"
                + " value (rec1, 1, 1, 1, 1)\nNode with depth 2, value (rec2, "
                + "2, 2, 2, 2)\nSkipList size is: 2\n", systemOut()
                    .getHistory());

        System.out.println();
    }


    /**
     * Tests the else branch of the processor() method.
     */
    public void testUnrecgonized() {
        cmdProc.processor("hello world");
        assertEquals("Unrecognized command.\n", systemOut().getHistory());
    }

}
