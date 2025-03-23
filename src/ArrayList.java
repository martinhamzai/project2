/**
 * This class implements an ArrayList data structure with select methods needed
 * for search command functionality.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-23
 * @param <E>
 *            Item
 */
public class ArrayList<E>
{

    private E[] listArray; // Array holding list elements
    private static final int DEFAULT_CAPACITY = 10; // default capacity
    private int capacity; // capacity of list
    private int size; // Current # of list items

    /**
     * Initializes a new ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList()
    {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        listArray = (E[])new Object[capacity];
    }


    /**
     * Inserts an item into the list.
     * 
     * @param item
     *            The item to insert
     */
    public void insert(E item)
    {
        // expand if at capacity
        if (size == capacity)
        {
            expand();
        }
        listArray[size] = item;
        size++;
    }


    /**
     * Expands the size of the array by increasing the capacity by 2x
     */
    @SuppressWarnings("unchecked")
    private void expand()
    {
        capacity *= 2;
        E[] newArray = (E[])new Object[capacity];
        for (int i = 0; i < size; i++)
        {
            newArray[i] = listArray[i];
        }
        listArray = newArray;
    }


    /**
     * @return the size of the ArrayList
     */
    public int size()
    {
        return size;
    }

    /**
     * Obtain the item at the specified position from the ArrayList.
     * 
     * @param pos
     *            The index to grab the item from
     * @return The item at the specified position
     * @throws IndexOutOfBoundsException
     *             if index is invalid
     */
    public E get(int pos)
        throws IndexOutOfBoundsException
    {
        if (pos >= size)
        {
            throw new IndexOutOfBoundsException(
                "Index " + pos + " is out of bounds");
        }
        return listArray[pos];
    }


    /**
     * Removes an item from the list at the specified position.
     * 
     * @param pos
     *            The index to remove the item from
     * @return The item at the specified position
     * @throws IndexOutOfBoundsException
     *             If index is invalid
     */
    public E remove(int pos)
        throws IndexOutOfBoundsException
    {
        if (pos >= size || pos < 0)
        {
            throw new IndexOutOfBoundsException(
                "Index" + pos + "is out of bounds");
        }
        E it = listArray[pos];
        // shift items
        for (int i = pos; i < size - 1; i++)
        {
            listArray[i] = listArray[i + 1];
        }
        listArray[size - 1] = null;
        size--;
        return it;
    }
    
    /**
     * true if the list contains the item
     * @param item
     *      the item being looked for
     * @return true if the list contains the item
     */
    public boolean contains(E item)
    {
        for (int i = 0; i < this.size(); i++)
        {
            if (item.equals(listArray[i]))
            {
                return true;
            }
        }
        return false;
    }

}
