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
     * Tests the getInstance method.
     */
    public void testGetInstance() {
        EmptyNode first = EmptyNode.getInstance();
        EmptyNode second = EmptyNode.getInstance();
        assertNotNull(first);
        assertNotNull(second);
        assertSame(first, second); 
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
        systemOut().clearHistory();
        res = en.dump(-1, 0, 0, 0);
        assertEquals(systemOut().getHistory(), "Node at 0, 0, 0: Empty\n");
    }
    
    /**
     * Tests the remove method.
     */
    public void testRemove() {
        assertTrue(en.remove(null, 0, 0, 0, 0) == null);
    }

    /**
     * Tests the regionsearch method
     */
    public void testRegionSearch() {
        en = EmptyNode.getInstance();
        int result = en.regionSearch(0, 0, 0, 0, 0, 0, 0);
        assertEquals(1, result);
    }
    
    /**
     * Tests the search method.
     */
    public void testSearch() {
        assertTrue(en.search(null, 0, 0, 0, 0) == null);
    }

}
