import student.TestCase;

public class InternalNodeTest extends TestCase 
{
    
    InternalNode node;
    
    
    public void setUp()
    {
        node = new InternalNode();
    }
    
    public void testDumpSingleNode() {
        
        node.insert(new KVPair<>("p1", new Point(1, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p2", new Point(2, 1)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p3", new Point(1, 2)), 0, 0, 4, 4);
        node.insert(new KVPair<>("p4", new Point(2, 2)), 0, 0, 4, 4);
        
        int count = node.dump(0, 0, 0, 4);
        assertEquals(9, count); 
        assertNotSame(2, count);
        assertNotSame(5, count);
        
    }
    
    
public void testSearch() {
        
    Point p = new Point(1, 1);
    Point p2 = new Point(3,3);
    Point p3 = new Point(1,3);
    Point p4 = new Point(3,1);

    KVPair<String, Point> result = node.search(p, 0, 0, 4, 4);
    assertNull("null", result);
    
    node.insert(new KVPair<>("p1", new Point(1, 1)), 0, 0, 4, 4);
    node.insert(new KVPair<>("p2", new Point(3, 3)), 0, 0, 4, 4);
    
    KVPair<String, Point> result2 = node.search(p, 0, 0, 4, 4);
    assertNotNull("not null", result2);
    assertEquals("p1", result2.key());
    }
    

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
        
    }
}
