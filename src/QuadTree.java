/**
 * QuadTree object that utilizes various types of QuadNodes to store KVPairs.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-23-2025
 */
public class QuadTree
{

    private QuadNode root;
    private final int size = 1024;

    /**
     * Create a new QuadTree with the root as an EmptyNode.
     */
    public QuadTree()
    {
        root = EmptyNode.getInstance();
    }


    /**
     * Insert a new KVPair into the tree.
     * 
     * @param pair
     *            the KVPair to insert
     */
    public void insert(KVPair<String, Point> pair)
    {
        root = root.insert(pair, 0, 0, size, size);
    }


    /**
     * Print out all of the nodes in the tree.
     */
    public void dump()
    {
        System.out.println("QuadTree dump:");
        int count = root.dump(0, 0, 0, size);
        System.out.println(count + " quadtree nodes printed");
    }


    /**
     * Print out all nodes inside a rectangular area in the quadtree
     * 
     * @param x
     *            search boundary
     * @param y
     *            search boundary
     * @param width
     *            search boundary
     * @param height
     *            search boundary
     * @return the number of nodes visited
     */
    public int regionSearch(int x, int y, int width, int height)
    {
        return root.regionSearch(x, y, width, height, 0, 0, size);
    }


    /**
     * search for a point in the quadtree
     * 
     * @param p
     *            Point to find
     * @return the KVPair of the point if found
     */
    public KVPair<String, Point> search(Point p)
    {
        return root.search(p, 0, 0, size, size);
    }


    /**
     * remove a point from the quadtree
     * 
     * @param p
     *            the point to remove
     */
    public void remove(Point p)
    {
        root = root.remove(p, 0, 0, size, size);
    }


    /**
     * find the duplicates in the tree and adds them to array list
     */
    public void findDup()
    {
        root.findDup();
    }
}
