/**
 * This class implements an ArrayList data structure with select methods needed
 * for search command functionality.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-02-22
 * @param <E>
 *            Item
 */
public class ArrayList<E> {

    private E[] listArray; // Array holding list elements
    private static final int DEFAULT_CAPACITY = 10; // default capacity
    private int capacity; // capacity of list
    private int size; // Current # of list items

    /**
     * Initializes a new ArrayList.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
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
    public void insert(E item) {
        // expand if at capacity
        if (size == capacity) {
            expand();
        }
        listArray[size] = item;
        size++;
    }


    /**
     * Expands the size of the array by increasing the capacity by 2x
     */
    @SuppressWarnings("unchecked")
    private void expand() {
        capacity *= 2;
        E[] newArray = (E[])new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = listArray[i];
        }
        listArray = newArray;
    }


    /**
     * @return the size of the ArrayList
     */
    public int size() {
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
    public E get(int pos) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Index " + pos
                + " is out of bounds");
        }
        return listArray[pos];
    }


    public E remove(int pos) throws IndexOutOfBoundsException {
        if (pos >= size) {
            throw new IndexOutOfBoundsException("Index" + pos
                + "is out of bounds");
        }
        E it = listArray[pos];
        listArray[pos] = null;
        size--;
        return it;
    }

}
