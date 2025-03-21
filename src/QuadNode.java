/**
 * Interface that describes a QuadNode which is implemented by various types
 * of nodes in the QuadTree
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-22-2025
 */
public interface QuadNode {

    /**
     * Inserts a KVPair
     * @param pair
     * @param x
     * @param y
     * @param width
     * @param height
     * @return the current QuadNode
     */
    public QuadNode insert(
        KVPair<String, Point> pair,
        int x,
        int y,
        int width,
        int height);


    /**
     * Prints out the node
     * @param depth
     * @param x
     * @param y
     * @param size
     * @return 1 for count
     */
    public int dump(int depth, int x, int y, int size);


    /**
     * Removes a point.
     * @param p
     * @param x
     * @param y
     * @param width
     * @param height
     * @return the current QuadNode
     */
    public QuadNode remove(Point p, int x, int y, int width, int height);


    /**
     * Searches for points in a region
     * 
     * @param searchX
     * @param searchY
     * @param searchWidth
     * @param searchHeight
     * @param currX
     * @param currY
     * @param size
     * 
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
     * @param p
     * @param x
     * @param y
     * @param width
     * @param height
     * @return The KVPair if found.
     */
    public KVPair<String, Point> search(
        Point p,
        int x,
        int y,
        int width,
        int height);
}
