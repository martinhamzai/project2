import student.TestCase;

/**
 * This class tests the ArrayList class so that the member methods work properly
 * and that the expected behavior occurs.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-3-22
 */
public class ArrayListTest extends TestCase {

    // ArrayList object for test methods
    private ArrayList<Integer> list;

    /**
     * Initializes an ArrayList object to be used for the tests.
     */
    public void setUp() {
        list = new ArrayList<Integer>();
    }


    /**
     * Tests the insert() method.
     */
    public void testInsert() {
        list.insert(1);
        assertTrue(list.size() == 1);
        assertTrue(list.get(0) == 1);
    }


    /**
     * Tests the expand functionality of the insert() method.
     */
    public void testInsertExpand() {
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        assertTrue(list.size() == 10);
        list.insert(10);
        assertTrue(list.size() == 11);
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i) == i);

        }
        int size = list.size();
        for (int i = size; i < 20; i++) {
            list.insert(i);
        }
        assertEquals(size + 9, list.size());
    }


    /**
     * Tests the get() method of the ArrayList and checks if it handles out of
     * bound indices properly.
     */
    public void testGet() {
        list.insert(1);
        list.insert(2);
        assertTrue(list.get(0) == 1);

        boolean exception = false;
        try {
            list.get(4);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertTrue(exception);

    }


    /**
     * Tests the remove() method.
     */
    public void testRemove() {
        
        // pos out
        boolean exception = false;
        try
        {
            list.remove(4);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = true;
        }
        assertTrue(exception);
        
        // removing at start
        list.insert(1);
        assertTrue(list.size() == 1);
        assertTrue(list.remove(0) == 1);
        assertTrue(list.size() == 0);
        
        //removing at middle
        list.insert(2); 
        list.insert(3); 
        list.insert(4);
        list.insert(5);
        assertTrue(list.remove(2) == 4);
        assertTrue(list.size() == 3);
        
        // remove at end
        assertTrue(list.remove(2) == 5);
        assertTrue(list.size() == 2);
        list.remove(0);
        list.remove(0);
        
        // negative index
        exception = false;
        try
        {
            list.remove(-1);
        }
        catch (IndexOutOfBoundsException e)
        {
            exception = true;
        }
        assertTrue(exception);
        
        // insert many then remove all
        for (int i = 0; i < 10; i++) {
            list.insert(i);
        }
        for (int i = 0; i < 10; i++) {
            list.remove(0);
            
        }
        
        // remove at size
        list.insert(1);
        exception = false;
        try
        {
            list.remove(1);
        }
        catch (IndexOutOfBoundsException e) {
            exception = true;
        }
        assertTrue(exception);
       
        
    }

}
