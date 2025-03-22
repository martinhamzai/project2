import java.util.Set;

/**
 * Interface that describes a QuadNode which is implemented by various types of
 * nodes in the QuadTree
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-22-2025
 */
public interface QuadNode
{

    /**
     * Inserts a KVPair
     * 
     * @param pair
     *            the pair
     * @param x
     *            the x
     * @param y
     *            the y
     * @param width
     *            the width
     * @param height
     *            the height
     * @return the current QuadNode
     */
    public
        QuadNode
        insert(KVPair<String, Point> pair, int x, int y, int width, int height);


    /**
     * Prints out the node
     * 
     * @param depth
     *            the depth
     * @param x
     *            the x
     * @param y
     *            the y
     * @param size
     *            the size
     * @return 1 for count
     */
    public int dump(int depth, int x, int y, int size);


    /**
     * Removes a point.
     * 
     * @param p
     *            the point
     * @param x
     *            the x
     * @param y
     *            the y
     * @param width
     *            the width
     * @param height
     *            the height
     * @return the current QuadNode
     */
    public QuadNode remove(Point p, int x, int y, int width, int height);


    /**
     * Searches for points in a region
     * 
     * @param searchX
     *            the search x
     * @param searchY
     *            the search y
     * @param searchWidth
     *            the search width
     * @param searchHeight
     *            the search height
     * @param currX
     *            the curr x
     * @param currY
     *            the curr y
     * @param size
     *            the size
     * @return the number of nodes visited
     */
    public int regionSearch(
        int searchX,
        int searchY,
        int searchWidth,
        int searchHeight,
        int currX,
        int currY,
        int size);


    /**
     * Searches for a point in the node.
     * 
     * @param p
     *            the point
     * @param x
     *            the x
     * @param y
     *            the y
     * @param width
     *            the width
     * @param height
     *            the height
     * @return The KVPair if found.
     */
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height);


    /**
     * finds the dups
     */
    public void findDup();


}
