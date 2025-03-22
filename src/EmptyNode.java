import java.util.Set;

/**
 * EmptyNode class that implements the QuadNode interface. The QuadTree points
 * to one instance of this class whenever an EmptyNode is needed to save space.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class EmptyNode
    implements QuadNode
{

    private static EmptyNode flyweight = null;

    private EmptyNode()
    {

    }


    /**
     * Returns the instantiated EmptyNode or creates one if it has not already
     * been instantiated.
     * 
     * @return the EmptyNode
     */
    public static EmptyNode getInstance()
    {
        if (flyweight == null)
        {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


    /**
     * Creates a new LeafNode if the QuadTree lands on the EmptyNode for
     * insertion.
     * 
     * @param pair
     *            KVPair to insert
     * @param x
     *            curr x
     * @param y
     *            curr y
     * @param width
     *            curr width
     * @param height
     *            curr height
     */
    @Override
    public
        QuadNode
        insert(KVPair<String, Point> pair, int x, int y, int width, int height)
    {
        return new LeafNode(pair);
    }


    /**
     * Prints out the information regarding the EmptyNode.
     * 
     * @param depth
     *            the depth of the node
     * @param x
     *            curr x
     * @param y
     *            curr y
     * @param size
     *            curr width and height
     */
    @Override
    public int dump(int depth, int x, int y, int size)
    {
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ": Empty");
        return 1;

    }


    /**
     * Redundant method inherited from the QuadNode interface.
     * 
     * @param p
     *            point
     * @param x
     *            curr x
     * @param y
     *            curr y
     * @param width
     *            curr width
     * @param height
     *            curr height
     * @return null
     */
    @Override
    public QuadNode remove(Point p, int x, int y, int width, int height)
    {
        return null;
    }


    /**
     * Returns 1 to indicate regionSearch hit the empty node.
     * 
     * @param searchX
     *            search boundary
     * @param searchY
     *            search boundary
     * @param searchWidth
     *            search boundary
     * @param searchHeight
     *            search boundary
     * @param currX
     *            current x
     * @param currY
     *            current y
     * @param size
     *            current width and height
     * @return 1
     */
    @Override
    public int regionSearch(
        int searchX,
        int searchY,
        int searchWidth,
        int searchHeight,
        int currX,
        int currY,
        int size)
    {

        return 1;
    }


    /**
     * Redundant method inherited from the QuadNode interface.
     * 
     * @param p
     *            point
     * @param x
     *            curr x
     * @param y
     *            curr y
     * @param width
     *            curr width
     * @param height
     *            curr height
     * @return null
     */
    @Override
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height)
    {
        return null;
    }
    
    /**
     * empty so no dups
     */
    public void findDup() {
        //nada
    }

}
