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
    private Database myWorld;
    private CommandProcessor cmdProc;

    /**
     * The setUp() method will be called automatically before each test and
     * reset whatever the test modified. For this test class, only a new
     * database object is needed, so create a database here for use in each test
     * case.
     */
    public void setUp() {
        myWorld = new Database();
        cmdProc = new CommandProcessor(myWorld);
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

        cmdProc.processor("insert p -1 1024");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, -1, 1024)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1024 -1024");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, 1024, -1024)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p -1 -1024");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, -1, -1024)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1030 1030");
        assertEquals(systemOut().getHistory(),
            "Point rejected: (p, 1030, 1030)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1 1");
        assertEquals(systemOut().getHistory(), "Point inserted: (p, 1, 1)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 2 4");
        assertEquals(systemOut().getHistory(), "Point inserted: (p, 2, 4)\n");

        systemOut().clearHistory();
        cmdProc.processor("insert p5 3 600");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (p5, 3, 600)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 0 1023");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (p, 0, 1023)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 1023 0");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (p, 1023, 0)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert pg 900 900");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (pg, 900, 900)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert py 850 850");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (py, 850, 850)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert pb 1023 1023");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (pb, 1023, 1023)\n");
        systemOut().clearHistory();

        cmdProc.processor("insert p 60 60");
        cmdProc.processor("insert p_p 234 543");
        cmdProc.processor("insert pip 234 214");
        cmdProc.processor("insert a 875 875");

        cmdProc.processor("dump");
        String str = systemOut().getHistory();
        assertEquals(str.substring(str.indexOf("QuadTree")), "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n" + "      Node at 0, 0, 128:\n"
            + "      (p, 1, 1)\n" + "      (p, 2, 4)\n" + "      (p, 60, 60)\n"
            + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128:\n" + "      (pip, 234, 214)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n" + "  Node at 512, 0, 512:\n"
            + "  (p, 1023, 0)\n" + "  Node at 0, 512, 512:\n"
            + "  (p5, 3, 600)\n" + "  (p, 0, 1023)\n" + "  (p_p, 234, 543)\n"
            + "  Node at 512, 512, 512: Internal\n"
            + "    Node at 512, 512, 256: Empty\n"
            + "    Node at 768, 512, 256: Empty\n"
            + "    Node at 512, 768, 256: Empty\n"
            + "    Node at 768, 768, 256: Internal\n"
            + "      Node at 768, 768, 128:\n" + "      (py, 850, 850)\n"
            + "      (a, 875, 875)\n" + "      Node at 896, 768, 128: Empty\n"
            + "      Node at 768, 896, 128: Empty\n"
            + "      Node at 896, 896, 128:\n" + "      (pg, 900, 900)\n"
            + "      (pb, 1023, 1023)\n" + "21 quadtree nodes printed\n");

        cmdProc.processor("remove 1 1");
        cmdProc.processor("remove 2 4");
        cmdProc.processor("remove 0 1023");
        cmdProc.processor("remove 1023 0");
        cmdProc.processor("remove 900 900");
        cmdProc.processor("remove 850 850");
        cmdProc.processor("remove 1023 1023");
        cmdProc.processor("remove 60 60");
        cmdProc.processor("remove 234 543");
        cmdProc.processor("remove 234 214");

        systemOut().clearHistory();
        cmdProc.processor("dump");
        String str2 = systemOut().getHistory();
        assertEquals(str2.substring(str2.indexOf("QuadTree")),
            "QuadTree dump:\n" + "Node at 0, 0, 1024:\n" + "(a, 875, 875)\n"
                + "(p5, 3, 600)\n" + "1 quadtree nodes printed\n");
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
        assertEquals(systemOut().getHistory(), "Point removed: (p, 1, 1)\n");

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

        systemOut().clearHistory();
        cmdProc.processor("remove -1 0");
        assertEquals(systemOut().getHistory(), "Point rejected: -1, 0\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 0 -1");
        assertEquals(systemOut().getHistory(), "Point rejected: 0, -1\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 1023 6");
        assertEquals(systemOut().getHistory(), "Point not found: (1023, 6)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 7 1023");
        assertEquals(systemOut().getHistory(), "Point not found: (7, 1023)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 0 1023");
        assertEquals(systemOut().getHistory(), "Point not found: (0, 1023)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 1023 0");
        assertEquals(systemOut().getHistory(), "Point not found: (1023, 0)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove -1023 -1");
        assertEquals(systemOut().getHistory(), "Point rejected: -1023, -1\n");

    }


    /**
     * Test most of the code dealing with Internal Nodes
     */
    public void testInternal() {
        cmdProc.processor("insert p1 1 1");
        cmdProc.processor("insert p2 2 2");
        cmdProc.processor("insert p3 3 3");
        cmdProc.processor("insert p4 4 4");
        cmdProc.processor("insert p5 5 5");
        cmdProc.processor("insert p900 900 900");
        cmdProc.processor("insert p901 901 901");
        cmdProc.processor("insert p902 902 902");
        cmdProc.processor("insert p601 600 1");
        cmdProc.processor("insert p602 600 2");
        cmdProc.processor("insert p603 600 3");
        cmdProc.processor("insert p604 600 4");
        cmdProc.processor("insert p301 1 600");
        cmdProc.processor("insert p302 2 600");
        cmdProc.processor("insert p303 3 600");
        cmdProc.processor("insert p304 4 600");

        systemOut().clearHistory();
        cmdProc.processor("remove 5 5");
        assertEquals(systemOut().getHistory(), "Point removed: (p5, 5, 5)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 3 600");
        assertEquals(systemOut().getHistory(),
            "Point removed: (p303, 3, 600)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 900 900");
        assertEquals(systemOut().getHistory(),
            "Point removed: (p900, 900, 900)\n");

        systemOut().clearHistory();
        cmdProc.processor("remove 600 2");
        assertEquals(systemOut().getHistory(),
            "Point removed: (p602, 600, 2)\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 20 20");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (-5, -5, 20, 20):\n"
                + "Point found: (p1, 1, 1)\n" + "Point found: (p2, 2, 2)\n"
                + "Point found: (p3, 3, 3)\n" + "Point found: (p4, 4, 4)\n"
                + "15 quadtree nodes visited\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch 50 50 400 400");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (50, 50, 400, 400):\n"
                + "15 quadtree nodes visited\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch 890 890 12 12");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (890, 890, 12, 12):\n"
                + "Point found: (p901, 901, 901)\n"
                + "2 quadtree nodes visited\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch 1000 1000 100 100");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (1000, 1000, 100, 100):\n"
                + "2 quadtree nodes visited\n");
    }


    /**
     * Tests the regionsearch branch of the processor() method.
     */
    public void testRegionSearch() {

        cmdProc.processor("insert p_p -1 -20");
        cmdProc.processor("insert poi 7 -8");
        cmdProc.processor("insert poi 1 1");
        cmdProc.processor("insert poi 1 2");
        cmdProc.processor("insert poi 2 2");
        cmdProc.processor("insert poi 3 3");
        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 5 5");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (-5, -5, 5, 5):\n"
                + "1 quadtree nodes visited\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 -5 20");
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (-5, -5, -5, 20)\n");
        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 20 -5");
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (-5, -5, 20, -5)\n");
        systemOut().clearHistory();
        cmdProc.processor("regionsearch -5 -5 -2 -2");
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (-5, -5, -2, -2)\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch 2 2 0 0");
        assertEquals(systemOut().getHistory(),
            "Rectangle rejected: (2, 2, 0, 0)\n");

        systemOut().clearHistory();
        cmdProc.processor("regionsearch 2 2 1 1");
        assertEquals(systemOut().getHistory(),
            "Points intersecting region (2, 2, 1, 1):\n"
                + "Point found: (poi, 2, 2)\n" + "10 quadtree nodes visited\n");
    }


    /**
     * Tests the duplicates branch of the processor() method.
     */
    public void testDuplicates() {
        cmdProc.processor("duplicates");
        assertEquals(systemOut().getHistory(), "Duplicate points:\n");

        cmdProc.processor("insert p1 1 1");
        cmdProc.processor("insert p2 2 2");
        cmdProc.processor("insert p3 3 3");
        cmdProc.processor("insert p4 4 4");
        cmdProc.processor("insert p5 5 5");
        cmdProc.processor("insert p900 900 900");
        cmdProc.processor("insert p901 901 901");
        cmdProc.processor("insert p902 902 902");
        cmdProc.processor("insert p601 600 1");
        cmdProc.processor("insert p602 600 2");
        cmdProc.processor("insert p603 600 3");
        cmdProc.processor("insert p604 600 4");
        cmdProc.processor("insert p301 1 600");
        cmdProc.processor("insert p302 2 600");
        cmdProc.processor("insert p303 3 600");
        cmdProc.processor("insert p304 4 600");
        cmdProc.processor("insert p11 1 1");
        cmdProc.processor("insert p111 1 1");
        cmdProc.processor("insert p1111 1 1");
        cmdProc.processor("insert p902 902 902");
        cmdProc.processor("insert p602 600 2");
        cmdProc.processor("insert p603 600 3");

        systemOut().clearHistory();
        cmdProc.processor("dump");
        String str = systemOut().getHistory();
        str = str.substring(str.indexOf("QuadTree"));
        assertEquals(str, "QuadTree dump:\n" + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n"
            + "      Node at 0, 0, 128: Internal\n"
            + "        Node at 0, 0, 64: Internal\n"
            + "          Node at 0, 0, 32: Internal\n"
            + "            Node at 0, 0, 16: Internal\n"
            + "              Node at 0, 0, 8: Internal\n"
            + "                Node at 0, 0, 4: Internal\n"
            + "                  Node at 0, 0, 2:\n"
            + "                  (p1, 1, 1)\n"
            + "                  (p11, 1, 1)\n"
            + "                  (p111, 1, 1)\n"
            + "                  (p1111, 1, 1)\n"
            + "                  Node at 2, 0, 2: Empty\n"
            + "                  Node at 0, 2, 2: Empty\n"
            + "                  Node at 2, 2, 2:\n"
            + "                  (p2, 2, 2)\n"
            + "                  (p3, 3, 3)\n"
            + "                Node at 4, 0, 4: Empty\n"
            + "                Node at 0, 4, 4: Empty\n"
            + "                Node at 4, 4, 4:\n"
            + "                (p4, 4, 4)\n" + "                (p5, 5, 5)\n"
            + "              Node at 8, 0, 8: Empty\n"
            + "              Node at 0, 8, 8: Empty\n"
            + "              Node at 8, 8, 8: Empty\n"
            + "            Node at 16, 0, 16: Empty\n"
            + "            Node at 0, 16, 16: Empty\n"
            + "            Node at 16, 16, 16: Empty\n"
            + "          Node at 32, 0, 32: Empty\n"
            + "          Node at 0, 32, 32: Empty\n"
            + "          Node at 32, 32, 32: Empty\n"
            + "        Node at 64, 0, 64: Empty\n"
            + "        Node at 0, 64, 64: Empty\n"
            + "        Node at 64, 64, 64: Empty\n"
            + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128: Empty\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n"
            + "  Node at 512, 0, 512: Internal\n"
            + "    Node at 512, 0, 256: Internal\n"
            + "      Node at 512, 0, 128: Internal\n"
            + "        Node at 512, 0, 64: Empty\n"
            + "        Node at 576, 0, 64: Internal\n"
            + "          Node at 576, 0, 32: Internal\n"
            + "            Node at 576, 0, 16: Empty\n"
            + "            Node at 592, 0, 16: Internal\n"
            + "              Node at 592, 0, 8: Empty\n"
            + "              Node at 600, 0, 8: Internal\n"
            + "                Node at 600, 0, 4: Internal\n"
            + "                  Node at 600, 0, 2:\n"
            + "                  (p601, 600, 1)\n"
            + "                  Node at 602, 0, 2: Empty\n"
            + "                  Node at 600, 2, 2: Internal\n"
            + "                    Node at 600, 2, 1:\n"
            + "                    (p602, 600, 2)\n"
            + "                    (p602, 600, 2)\n"
            + "                    Node at 601, 2, 1: Empty\n"
            + "                    Node at 600, 3, 1:\n"
            + "                    (p603, 600, 3)\n"
            + "                    (p603, 600, 3)\n"
            + "                    Node at 601, 3, 1: Empty\n"
            + "                  Node at 602, 2, 2: Empty\n"
            + "                Node at 604, 0, 4: Empty\n"
            + "                Node at 600, 4, 4:\n"
            + "                (p604, 600, 4)\n"
            + "                Node at 604, 4, 4: Empty\n"
            + "              Node at 592, 8, 8: Empty\n"
            + "              Node at 600, 8, 8: Empty\n"
            + "            Node at 576, 16, 16: Empty\n"
            + "            Node at 592, 16, 16: Empty\n"
            + "          Node at 608, 0, 32: Empty\n"
            + "          Node at 576, 32, 32: Empty\n"
            + "          Node at 608, 32, 32: Empty\n"
            + "        Node at 512, 64, 64: Empty\n"
            + "        Node at 576, 64, 64: Empty\n"
            + "      Node at 640, 0, 128: Empty\n"
            + "      Node at 512, 128, 128: Empty\n"
            + "      Node at 640, 128, 128: Empty\n"
            + "    Node at 768, 0, 256: Empty\n"
            + "    Node at 512, 256, 256: Empty\n"
            + "    Node at 768, 256, 256: Empty\n"
            + "  Node at 0, 512, 512: Internal\n"
            + "    Node at 0, 512, 256: Internal\n"
            + "      Node at 0, 512, 128: Internal\n"
            + "        Node at 0, 512, 64: Empty\n"
            + "        Node at 64, 512, 64: Empty\n"
            + "        Node at 0, 576, 64: Internal\n"
            + "          Node at 0, 576, 32: Internal\n"
            + "            Node at 0, 576, 16: Empty\n"
            + "            Node at 16, 576, 16: Empty\n"
            + "            Node at 0, 592, 16: Internal\n"
            + "              Node at 0, 592, 8: Empty\n"
            + "              Node at 8, 592, 8: Empty\n"
            + "              Node at 0, 600, 8: Internal\n"
            + "                Node at 0, 600, 4:\n"
            + "                (p301, 1, 600)\n"
            + "                (p302, 2, 600)\n"
            + "                (p303, 3, 600)\n"
            + "                Node at 4, 600, 4:\n"
            + "                (p304, 4, 600)\n"
            + "                Node at 0, 604, 4: Empty\n"
            + "                Node at 4, 604, 4: Empty\n"
            + "              Node at 8, 600, 8: Empty\n"
            + "            Node at 16, 592, 16: Empty\n"
            + "          Node at 32, 576, 32: Empty\n"
            + "          Node at 0, 608, 32: Empty\n"
            + "          Node at 32, 608, 32: Empty\n"
            + "        Node at 64, 576, 64: Empty\n"
            + "      Node at 128, 512, 128: Empty\n"
            + "      Node at 0, 640, 128: Empty\n"
            + "      Node at 128, 640, 128: Empty\n"
            + "    Node at 256, 512, 256: Empty\n"
            + "    Node at 0, 768, 256: Empty\n"
            + "    Node at 256, 768, 256: Empty\n"
            + "  Node at 512, 512, 512: Internal\n"
            + "    Node at 512, 512, 256: Empty\n"
            + "    Node at 768, 512, 256: Empty\n"
            + "    Node at 512, 768, 256: Empty\n"
            + "    Node at 768, 768, 256: Internal\n"
            + "      Node at 768, 768, 128: Empty\n"
            + "      Node at 896, 768, 128: Empty\n"
            + "      Node at 768, 896, 128: Empty\n"
            + "      Node at 896, 896, 128: Internal\n"
            + "        Node at 896, 896, 64: Internal\n"
            + "          Node at 896, 896, 32: Internal\n"
            + "            Node at 896, 896, 16: Internal\n"
            + "              Node at 896, 896, 8: Internal\n"
            + "                Node at 896, 896, 4: Empty\n"
            + "                Node at 900, 896, 4: Empty\n"
            + "                Node at 896, 900, 4: Empty\n"
            + "                Node at 900, 900, 4: Internal\n"
            + "                  Node at 900, 900, 2:\n"
            + "                  (p900, 900, 900)\n"
            + "                  (p901, 901, 901)\n"
            + "                  Node at 902, 900, 2: Empty\n"
            + "                  Node at 900, 902, 2: Empty\n"
            + "                  Node at 902, 902, 2:\n"
            + "                  (p902, 902, 902)\n"
            + "                  (p902, 902, 902)\n"
            + "              Node at 904, 896, 8: Empty\n"
            + "              Node at 896, 904, 8: Empty\n"
            + "              Node at 904, 904, 8: Empty\n"
            + "            Node at 912, 896, 16: Empty\n"
            + "            Node at 896, 912, 16: Empty\n"
            + "            Node at 912, 912, 16: Empty\n"
            + "          Node at 928, 896, 32: Empty\n"
            + "          Node at 896, 928, 32: Empty\n"
            + "          Node at 928, 928, 32: Empty\n"
            + "        Node at 960, 896, 64: Empty\n"
            + "        Node at 896, 960, 64: Empty\n"
            + "        Node at 960, 960, 64: Empty\n"
            + "133 quadtree nodes printed\n");
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
            + "1, Value (null)\nSkipList size is: 0\nQuadTree"
            + " dump:\nNode at 0,"
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
            + "SkipList size is: 4\n" + "QuadTree dump:\n"
            + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n" + "      Node at 0, 0, 128:\n"
            + "      (p_p, 1, 20)\n" + "      (poi, 10, 30)\n"
            + "      (p_42, 1, 20)\n" + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128:\n" + "      (far, 200, 200)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n"
            + "  Node at 512, 0, 512: Empty\n"
            + "  Node at 0, 512, 512: Empty\n"
            + "  Node at 512, 512, 512: Empty\n" + "13 quadtree nodes printed");

    }


    /**
     * Tests the dump branch of the processor() method more
     */
    public void testDump2() {
        cmdProc.processor("insert p_p 512 512");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (p_p, 512, 512)\n");
        systemOut().clearHistory();
        cmdProc.processor("insert p2 1023 1023");
        assertEquals(systemOut().getHistory(),
            "Point inserted: (p2, 1023, 1023)\n");

        systemOut().clearHistory();
        cmdProc.processor("dump");
        assertFalse(systemOut().getHistory().equals(
            "SkipList dump:\nNode has depth "
                + "1, Value (null)\nSkipList size is: 0\nQuadTree"
                + " dump:\nNode "
                + "at 0, 0, 1024: Empty\n1 quadtree nodes printed\n"));

    }


    /**
     * Tests the dump branch of the processor() method more
     */
    public void testBig() {

        cmdProc.processor("insert p1 1 1");
        cmdProc.processor("insert p2 1 3");
        cmdProc.processor("insert p3 3 1");
        cmdProc.processor("insert p4 2 2");
        cmdProc.processor("insert p5 2 4");
        cmdProc.processor("insert p6 2 4");
        cmdProc.processor("insert p7 2 4");
        cmdProc.processor("insert p8 5 3");
        cmdProc.processor("insert p9 5 4");
        cmdProc.processor("insert p10 5 7");
        cmdProc.processor("insert p11 1 7");
        cmdProc.processor("insert p12 8 1");
        cmdProc.processor("insert p13 9 3");
        cmdProc.processor("insert p14 8 8");
        cmdProc.processor("insert p15 9 9");
        cmdProc.processor("insert p16 7 8");
        cmdProc.processor("insert p17 2 2");

        cmdProc.processor("remove p11");
        cmdProc.processor("remove 2 4");
        cmdProc.processor("insert dup 2 2");
        cmdProc.processor("remove p14");

        cmdProc.processor("regionsearch 4 4 2 2");
        cmdProc.processor("duplicates");
        cmdProc.processor("search p5");

        assertEquals(systemOut().getHistory(), "Point inserted: (p1, 1, 1)\r\n"
            + "Point inserted: (p2, 1, 3)\r\n"
            + "Point inserted: (p3, 3, 1)\r\n"
            + "Point inserted: (p4, 2, 2)\r\n"
            + "Point inserted: (p5, 2, 4)\r\n"
            + "Point inserted: (p6, 2, 4)\r\n"
            + "Point inserted: (p7, 2, 4)\r\n"
            + "Point inserted: (p8, 5, 3)\r\n"
            + "Point inserted: (p9, 5, 4)\r\n"
            + "Point inserted: (p10, 5, 7)\r\n"
            + "Point inserted: (p11, 1, 7)\r\n"
            + "Point inserted: (p12, 8, 1)\r\n"
            + "Point inserted: (p13, 9, 3)\r\n"
            + "Point inserted: (p14, 8, 8)\r\n"
            + "Point inserted: (p15, 9, 9)\r\n"
            + "Point inserted: (p16, 7, 8)\r\n"
            + "Point inserted: (p17, 2, 2)\r\n"
            + "Point removed: (p11, 1, 7)\r\n" + "Point removed: (p7, 2, 4)\r\n"
            + "Point inserted: (dup, 2, 2)\r\n"
            + "Point removed: (p14, 8, 8)\r\n"
            + "Points intersecting region (4, 4, 2, 2):\r\n"
            + "Point found: (p9, 5, 4)\r\n" + "9 quadtree nodes visited\r\n"
            + "Duplicate points:\r\n" + "(2, 2)\r\n" + "(2, 4)\r\n"
            + "Found (p5, 2, 4)\r\n");

        systemOut().clearHistory();
        cmdProc.processor("dump");
        String test = systemOut().getHistory();
        test = test.substring(test.indexOf("QuadTree"));
        assertEquals(test, "QuadTree dump:\n" + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n"
            + "      Node at 0, 0, 128: Internal\n"
            + "        Node at 0, 0, 64: Internal\n"
            + "          Node at 0, 0, 32: Internal\n"
            + "            Node at 0, 0, 16: Internal\n"
            + "              Node at 0, 0, 8: Internal\n"
            + "                Node at 0, 0, 4: Internal\n"
            + "                  Node at 0, 0, 2:\n"
            + "                  (p1, 1, 1)\n"
            + "                  Node at 2, 0, 2:\n"
            + "                  (p3, 3, 1)\n"
            + "                  Node at 0, 2, 2:\n"
            + "                  (p2, 1, 3)\n"
            + "                  Node at 2, 2, 2:\n"
            + "                  (p4, 2, 2)\n"
            + "                  (p17, 2, 2)\n"
            + "                  (dup, 2, 2)\n"
            + "                Node at 4, 0, 4:\n"
            + "                (p8, 5, 3)\n"
            + "                Node at 0, 4, 4:\n"
            + "                (p6, 2, 4)\n" + "                (p5, 2, 4)\n"
            + "                Node at 4, 4, 4:\n"
            + "                (p9, 5, 4)\n" + "                (p10, 5, 7)\n"
            + "              Node at 8, 0, 8:\n" + "              (p12, 8, 1)\n"
            + "              (p13, 9, 3)\n" + "              Node at 0, 8, 8:\n"
            + "              (p16, 7, 8)\n" + "              Node at 8, 8, 8:\n"
            + "              (p15, 9, 9)\n"
            + "            Node at 16, 0, 16: Empty\n"
            + "            Node at 0, 16, 16: Empty\n"
            + "            Node at 16, 16, 16: Empty\n"
            + "          Node at 32, 0, 32: Empty\n"
            + "          Node at 0, 32, 32: Empty\n"
            + "          Node at 32, 32, 32: Empty\n"
            + "        Node at 64, 0, 64: Empty\n"
            + "        Node at 0, 64, 64: Empty\n"
            + "        Node at 64, 64, 64: Empty\n"
            + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128: Empty\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n"
            + "  Node at 512, 0, 512: Empty\n"
            + "  Node at 0, 512, 512: Empty\n"
            + "  Node at 512, 512, 512: Empty\n"
            + "37 quadtree nodes printed\n");

    }


    /**
     * Tests the remove by name branch of the processor() method.
     */
    public void testRemoveAll() {
        cmdProc.processor("insert p1 1 1");
        cmdProc.processor("insert p2 1 3");
        cmdProc.processor("insert p3 3 1");
        cmdProc.processor("insert p4 2 2");
        cmdProc.processor("insert p5 2 4");
        cmdProc.processor("remove p1");
        cmdProc.processor("remove 1 3");
        cmdProc.processor("remove p3");
        cmdProc.processor("search p4");
        cmdProc.processor("remove 2 2");
        cmdProc.processor("remove 2 4");
        systemOut().clearHistory();
        cmdProc.processor("dump");

        String test = systemOut().getHistory();
        test = test.substring(test.indexOf("SkipList dump"));
        // assertFuzzyEquals(test, "");
    }


    /**
     * Extra testing.
     */

    public void testMore() {
        cmdProc.processor("insert p1 1 1");
        cmdProc.processor("insert p2 1 3");
        cmdProc.processor("insert p3 3 1");
        cmdProc.processor("insert p4 2 2");
        cmdProc.processor("insert p5 2 4");
        cmdProc.processor("remove p1");
        cmdProc.processor("remove 1 3");
        cmdProc.processor("remove p3");
        cmdProc.processor("remove 2 2");
        systemOut().clearHistory();
        cmdProc.processor("dump");
        String str = systemOut().getHistory();
        assertEquals(str.substring(str.indexOf("QuadTree")),
            "QuadTree dump:\r\n" + "Node at 0, 0, 1024:\r\n" + "(p5, 2, 4)\r\n"
                + "1 quadtree nodes printed\n");
    }

}
