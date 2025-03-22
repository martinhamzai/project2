import student.TestCase;

/**
 * Test class for LeafNode.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class InternalNodeTest
    extends TestCase
{

    private InternalNode node;

    /**
     * Setup for the test cases
     */
    public void setUp()
    {
        node = new InternalNode();
    }


    /**
     * tests the dump method further
     */
    public void testDumpSingleNode()
    {

        node.insert(new KVPair<>("p1", new Point(1, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p2", new Point(2, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p3", new Point(1, 2)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p4", new Point(2, 2)), 0, 0, 4, 4);

        int count = node.dump(0, 0, 0, 4);
        assertEquals(9, count);
        assertNotSame(2, count);
        assertNotSame(5, count);

    }


    /**
     * tests the search method further
     */
    public void testSearch()
    {

        KVPair<String, Point> p1 = new KVPair<>("p1", new Point(1, 1));
        KVPair<String, Point> p2 = new KVPair<>("p1", new Point(1, 3));
        KVPair<String, Point> p3 = new KVPair<>("p1", new Point(3, 1));
        KVPair<String, Point> p4 = new KVPair<>("p1", new Point(3, 3));
        Point p = new Point(5, 5);
        KVPair<String, Point> result1 = node.search(p, 0, 0, 4, 4);
        assertNull("null", result1);
        Point pp = new Point(-1, -1);
        KVPair<String, Point> result2 = node.search(pp, 0, 0, 4, 4);
        assertNull("null", result2);

        node.insert(p1, 0, 0, 4, 4);
        node.insert(p1, 0, 0, 4, 4);
        node.insert(p1, 0, 0, 4, 4);
        node.insert(p1, 0, 0, 4, 4);
        node.insert(p1, 0, 0, 4, 4);
        node.insert(p2, 0, 0, 4, 4);
        node.insert(p3, 0, 0, 4, 4);
        node.insert(p4, 0, 0, 4, 4);

        KVPair<String, Point> result3 = node.search(p1.value(), 0, 0, 4, 4);
        assertNotNull("not null", result3);
        assertEquals("p1", result3.key());
        KVPair<String, Point> result4 = node.search(p2.value(), 0, 0, 4, 4);
        assertNotNull("not null", result4);
        assertEquals("p1", result4.key());
        KVPair<String, Point> result5 = node.search(p3.value(), 0, 0, 4, 4);
        assertNotNull("not null", result5);
        assertEquals("p1", result5.key());
        KVPair<String, Point> result6 = node.search(p4.value(), 0, 0, 4, 4);
        assertNotNull("not null", result6);
        assertEquals("p1", result6.key());

    }


    /**
     * tests the regionsearch method further
     */
    public void testRegionSearch()
    {
        node.insert(new KVPair<>("p1", new Point(1, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p2", new Point(2, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p3", new Point(1, 2)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p4", new Point(2, 2)), 0, 0, 4, 4);

        assertEquals(node.regionSearch(0, 0, 4, 4, 0, 0, 4), 9);
        assertNotSame(node.regionSearch(2, 2, 4, 4, 0, 0, 4), 4);
        assertEquals(node.regionSearch(2, 2, 4, 4, 0, 0, 4), 2);
        assertEquals(node.regionSearch(4, 4, 4, 4, 0, 0, 4), 1);
        assertEquals(node.regionSearch(5, 4, 4, 4, 0, 0, 4), 1);
        assertEquals(node.regionSearch(20, 20, 4, 4, 0, 0, 4), 1);
        assertEquals(node.regionSearch(-1, -2, 2, 2, 0, 0, 4), 1);
        assertNotSame(node.regionSearch(2, 2, 4, 4, 0, 0, 4), 6);
        assertNotSame(node.regionSearch(4, 4, 4, 4, 0, 0, 4), 0);
        assertNotSame(node.regionSearch(5, 4, 4, 4, 0, 0, 4), 3);
        assertNotSame(node.regionSearch(20, 20, 4, 4, 0, 0, 4), 2);
        assertNotSame(node.regionSearch(-1, -2, 2, 2, 0, 0, 4), 6);

    }


    /**
     * tests the remove method further
     */
    public void testRemove()
    {
        KVPair<String, Point> p1 = new KVPair<>("p1", new Point(512, 512));
        KVPair<String, Point> p2 = new KVPair<>("p1", new Point(511, 511));
        KVPair<String, Point> p3 = new KVPair<>("p1", new Point(512, 511));
        KVPair<String, Point> p4 = new KVPair<>("p1", new Point(511, 512));
        node.insert(p1, 0, 0, 1023, 1023);
        node.insert(p2, 0, 0, 1023, 1023);
        node.insert(p3, 0, 0, 1023, 1023);
        node.insert(p4, 0, 0, 1023, 1023);
        node.remove(p1.value(), 0, 0, 1023, 1023);
        node.remove(p2.value(), 0, 0, 1023, 1023);
        node.remove(p3.value(), 0, 0, 1023, 1023);
        node.remove(p4.value(), 0, 0, 1023, 1023);
        assertNotSame(node, EmptyNode.getInstance());
    }


    /**
     * test insert()
     */
    public void testInsert()
    {
        KVPair<String, Point> p1 = new KVPair<>("p1", new Point(512, 512));
        KVPair<String, Point> p2 = new KVPair<>("p1", new Point(600, 674));
        KVPair<String, Point> p3 = new KVPair<>("p1", new Point(842, 540));
        KVPair<String, Point> p4 = new KVPair<>("p1", new Point(1010, 1000));
        node.insert(p1, 0, 0, 1024, 1024);
        node.dump(0, 0, 1024, 1024);
        assertEquals(
            systemOut().getHistory(),
            "Node at 0, 1024, 1024:" + " Internal\r\n"
                + "  Node at 0, 1024, 512:\r\n" + "    (p1, 512, 512)\r\n"
                + "  Node at 512, 1024, 512: Empty\r\n"
                + "  Node at 0, 1536, 512: Empty\r\n"
                + "  Node at 512, 1536, 512: Empty\n");
        systemOut().clearHistory();
        node.insert(p2, 0, 0, 1024, 1024);
        node.insert(p3, 0, 0, 1024, 1024);
        node.insert(p4, 0, 0, 1024, 1024);
        node.dump(0, 0, 1024, 1024);
        assertEquals(
            systemOut().getHistory(),
            "Node at 0, 1024, 1024:" + " Internal\r\n"
                + "  Node at 0, 1024, 512:\r\n" + "    (p1, 512, 512)\r\n"
                + "  Node at 512, 1024, 512: Empty\r\n"
                + "  Node at 0, 1536, 512: Empty\r\n"
                + "  Node at 512, 1536, 512:\n" + "    (p1, 600, 674)\r\n"
                + "    (p1, 842, 540)\r\n" + "    (p1, 1010, 1000)\n");

    }
    
}
