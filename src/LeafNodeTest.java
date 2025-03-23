import student.TestCase;

/**
 * Test class for LeafNode.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class LeafNodeTest extends TestCase {

    private LeafNode ln;
    private Point p1;
    private Point p2;
    private KVPair<String, Point> pair1;
    private KVPair<String, Point> pair2;

    /**
     * Setup for each test case.
     */
    public void setUp() {
        p1 = new Point(10, 10);
        p2 = new Point(20, 20);
        pair1 = new KVPair<>("p1", p1);
        pair2 = new KVPair<>("p2", p2);
        ln = new LeafNode(pair1);
    }


    /**
     * Tests that a new node stores the initial pair correctly
     */
    public void testInsert() {
        //test the point comparison used in the insert method
        Point pt1 = new Point(1, 1);
        Point pt5 = new Point(1, 1);
        Point pt2 = new Point(2, 1);
        Point pt3 = new Point(1, 2);
        Point pt4 = new Point(4, 4);
        String test = "test";
        assertFalse(pt1.equals(pt2));
        assertFalse(pt1.equals(pt3));
        assertFalse(pt1.equals(pt4));
        assertTrue(pt1.equals(pt5));
        
        //test insest
        KVPair<String, Point> p3 = new KVPair<>("p12", new Point(10, 10));
        QuadNode result = ln.insert(p3, 0, 0, 1024, 1024);
        ArrayList<KVPair<String, Point>> list2 = new ArrayList<>();
        
        assertTrue(result instanceof LeafNode);
        LeafNode leaf = (LeafNode)result;
        assertEquals(2, leaf.getPairs().size());
    }


    /**
     * Test insert that changes the leafnode into internalnode.
     */
    public void testInsertToInternal() {
        ln = new LeafNode(new KVPair<>("a", new Point(1, 1)));
        ln = (LeafNode)ln.insert(pair1, 0, 0, 1024, 1024);
        ln = (LeafNode)ln.insert(pair2, 0, 0, 1024, 1024);

        // this should change to internalnode
        QuadNode changed = ln.insert(new KVPair<>("d", new Point(4, 4)), 0, 0,
            1024, 1024);
        assertTrue(changed instanceof InternalNode);
    }


    /**
     * Test dumping the node prints the right info.
     */
    public void testDump() {
        ln.insert(pair2, 0, 0, 1024, 1024);
        int count = ln.dump(0, 0, 0, 1024);
        assertEquals(1, count);
        assertEquals(systemOut().getHistory(), "Node at 0, 0, 1024:\n" + pair1
            .toString() + "\n" + pair2.toString() + "\n");
    }


    /**
     * Test region search.
     */
    public void testRegionSearch() {
        ln.insert(pair2, 0, 0, 1024, 1024);

        // region includes p1
        int count1 = ln.regionSearch(0, 0, 11, 11, 0, 0, 1024);
        assertEquals(1, count1);
        assertEquals(systemOut().getHistory(), "Point found: " + pair1
            .toString() + "\n");

        systemOut().clearHistory();
        ln.insert(pair1, 0, 0, 1024, 1024);

        int count2 = ln.regionSearch(0, 0, 100, 100, 0, 0, 1024);
        assertEquals(1, count2); // node still visited
        assertFalse(count2 == 5);
    }


    /**
     * Test search method
     */
    public void testSearch() {
        ln.insert(pair2, 0, 0, 1024, 1024);
        KVPair<String, Point> result = ln.search(p1, 0, 0, 1024, 1024);
        assertEquals(pair1, result);
        assertNull(ln.search(new Point(100, 100), 0, 0, 1024, 1024));
    }


    /**
     * Test remove method.
     */
    public void testRemove() {
        QuadNode removed = ln.remove(p1, 0, 0, 1024, 1024);
        assertTrue(removed instanceof EmptyNode);
        assertEquals(systemOut().getHistory(), "Point removed: (p1, 10, 10)\n");

        systemOut().clearHistory();

        removed = ln.remove(new Point(111, 111), 0, 0, 1024, 1024);
        assertSame(ln, removed);
        assertEquals(systemOut().getHistory(), "Point not found: (111, 111)\n");
    }

}
