import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Random;
import student.TestableRandom;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-02-25
 * @param <K>
 *            Key
 * @param <E>
 *            Element
 */
public class SkipList<K extends Comparable<K>, E>
    implements Iterable<KVPair<K, E>>
{
    private SkipNode head; // First element (Sentinel Node)
    private int size; // number of entries in the Skip List
    private Random rng;

    /**
     * Initializes the fields head, size and level
     */
    public SkipList()
    {
        head = new SkipNode(null, 1);
        size = 0;
        this.rng = new TestableRandom();
    }


    /**
     * @return a random level (using geometric distribution), minimum of 1
     */
    public int randomLevel()
    {
        int level = 1;
        while (rng.nextBoolean())
            level++;
        return level;
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     * 
     * @param key
     *            key to be searched for
     * @return fill out later
     */
    public ArrayList<KVPair<K, E>> search(K key)
    {
        SkipNode x = head;
        // ArrayList to store pairs
        ArrayList<KVPair<K, E>> pairs = new ArrayList<>();
        // Find the first key that matches the search target
        for (int i = head.getLevel(); i >= 0; i--)
        { // for each level
            while ((x.forward[i] != null)
                && (x.forward[i].element().key().compareTo(key) < 0))
            {
                x = x.forward[i];
            }
        }
        // move to first match
        x = x.forward[0];
        // insert all matching keys into the ArrayList
        while ((x != null) && x.element().key().compareTo(key) == 0)
        {
            pairs.insert(x.element());
            x = x.forward[0];
        }
        return pairs;
    }


    /**
     * @return the size of the SkipList
     */
    public int size()
    {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     * 
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, E> it)
    {
        int newLevel = randomLevel(); // new nodes level
        // if the level of the new node is higher than the head adjust head to
        // same level
        if (newLevel > head.getLevel())
        {
            adjustHead(newLevel);
        }
        // locate the correct position for the new node
        SkipNode[] update = (SkipNode[])Array
            .newInstance(SkipList.SkipNode.class, head.getLevel() + 1);
        SkipNode x = head;
        for (int i = head.getLevel(); i >= 0; i--)
        { // Find insert position
            while ((x.forward[i] != null)
                && (x.forward[i].element().key().compareTo(it.key()) < 0))
            {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        // create node and update pointers
        x = new SkipNode(it, newLevel);
        for (int i = 0; i <= newLevel; i++)
        { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // increment list size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    public void adjustHead(int newLevel)
    {
        if (newLevel < head.getLevel())
        {
            head.level = newLevel;
        }
        else
        {
            SkipNode temp = head;
            head = new SkipNode(null, newLevel);
            for (int i = 0; i <= temp.getLevel(); i++)
                head.forward[i] = temp.forward[i];
        }
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the name of the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, E> remove(K key)
    {
        // locate the correct position for the new node
        SkipNode[] update = (SkipNode[])Array
            .newInstance(SkipList.SkipNode.class, head.getLevel() + 1);
        SkipNode x = head;
        for (int i = head.getLevel(); i >= 0; i--)
        { // Find remove position
            while ((x.forward[i] != null)
                && (x.forward[i].element().key().compareTo(key) < 0))
            {
                x = x.forward[i];
            }
            update[i] = x; // Track end at level i
        }
        // move to node to be removed
        x = x.forward[0];

        if (x != null && x.element().key().compareTo(key) == 0)
        {
            KVPair<K, E> item = x.element();
            for (int i = 0; i <= head.getLevel(); i++)
            {
                if (update[i].forward[i] != x)
                {
                    break;
                }
                update[i].forward[i] = x.forward[i];
            }

            // if the top level of head now points to nothing
            while (head.getLevel() > 1 && head.forward[head.getLevel()] == null)
            {
                adjustHead(head.getLevel() - 1);
            }

            size--;
            return item;
        }
        return null;
    }


    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, E> removeByValue(E val)
    {
        // locate the correct position for node to remove using iterator
        SkipListIterator iter = new SkipListIterator();
        while (iter.hasNext())
        {
            KVPair<K, E> curr = iter.next();
            // call remove by key for the first match
            if (curr.value().equals(val))
            {
                KVPair<K, E> item = remove(curr.key());
                return item;
            }
        }
        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump()
    {
        SkipListIterator iter = new SkipListIterator();
        System.out.println("SkipList dump:");
        System.out
            .println("Node has depth " + iter.getLevel() + ", Value (null)");
        while (iter.hasNext())
        {
            KVPair<K, E> curr = iter.next();
            System.out.print("Node has depth " + iter.getLevel() + ", Value ");
            System.out.println(curr.toString());
        }
        System.out.println("SkipList size is: " + size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     * 
     * @author Martin Hamzai and Richmond Southall
     * @version 2025-02-22
     */
    private class SkipNode
    {

        // the KVPair to hold
        private KVPair<K, E> pair;
        // An array of pointers to subsequent nodes
        private SkipNode[] forward;
        // the level of the node
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         * 
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> tempPair, int level)
        {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipNode.class, level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         * 
         * @return the KVPair
         */
        public KVPair<K, E> element()
        {
            return pair;
        }


        /**
         * @return the level of the SkipNode.
         */
        public int getLevel()
        {
            return level;
        }
    }


    /**
     * Iterator class to traverse a SkipList.
     * 
     * @author Martin Hamzai and Richmond Southall
     * @version 2025-02-22
     */
    private class SkipListIterator
        implements Iterator<KVPair<K, E>>
    {
        private SkipNode current;

        /**
         * Constructor method for SkipListIterator
         */
        public SkipListIterator()
        {
            current = head;
        }


        /**
         * Checks if the SkipList has a next node.
         * 
         * @return true if has next node
         */
        @Override
        public boolean hasNext()
        {
            return current.forward[0] != null;
        }


        /**
         * Returns the next KVPair in the SkipList.
         * 
         * @return the next KVPair
         */
        @Override
        public KVPair<K, E> next()
        {
            KVPair<K, E> elem = current.forward[0].element();
            current = current.forward[0];
            return elem;
        }


        /**
         * @return the level of the current node
         */
        public int getLevel()
        {
            return current.getLevel();
        }

    }

    /**
     * @return a new SkipListIterator
     */
    @Override
    public Iterator<KVPair<K, E>> iterator()
    {
        return new SkipListIterator();
    }

}
