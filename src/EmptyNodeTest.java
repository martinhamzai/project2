import student.TestCase;

/**
 * This class tests the EmptyNode class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Martin Hamzai and Richmond Southall
 * 
 * @version 03-22-2025
 */
public class EmptyNodeTest extends TestCase {
    
    private EmptyNode en;
    
    /**
     * Initializes an EmptyNode object to be used for the tests.
     */
    public void setUp() {
        en = EmptyNode.getInstance();
    }
    
    /**
     * Tests the insert method.
     */
    public void testInsert() {
        assertTrue(en.insert(null, 0, 0, 0, 0) instanceof LeafNode);
    }
    
    /**
     * Tests the dump method.
     */
    public void testDump() {
        int res = en.dump(1, 0, 0, 0);
        assertTrue(res == 1);
        assertEquals(systemOut().getHistory(), "  Node at 0, 0, 0: Empty\n");
    }
    
    /**
     * Tests the remove method.
     */
    public void testRemove() {
        assertTrue(en.remove(null, 0, 0, 0, 0) == null);
    }
    
    /**
     * Tests the regionSearch method
     */
    
    public void testRegionSearch() {
        assertTrue(en.regionSearch(0, 0, 0, 0, 0, 0, 0) == 1);
    }
    
    /**
     * Tests the search method
     */
    public void testSearch() {
        assertTrue(en.search(null, 0, 0, 0, 0) == null);
    }

}
