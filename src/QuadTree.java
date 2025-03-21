/**
 * QuadTree object that utilizes various types of QuadNodes to store KVPairs.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-22-2025
 */
public class QuadTree {

    private QuadNode root;
    private final int SIZE = 1024;

    /**
     * Create a new QuadTree with the root as an EmptyNode.
     */
    public QuadTree() {
        root = EmptyNode.getInstance();
    }


    /**
     * Insert a new KVPair into the tree.
     * 
     * @param pair
     * @return true
     */
    public boolean insert(KVPair<String, Point> pair) {
        root = root.insert(pair, 0, 0, SIZE - 1, SIZE - 1);
        return true;
    }


    /**
     * Print out all of the nodes in the tree.
     */
    public void dump() {
        System.out.println("QuadTree dump:");
        int count = root.dump(0, 0, 0, SIZE);
        System.out.println(count + " quadtree nodes printed");
    }


    /**
     * Print out all nodes inside a rectangular area in the quadtree
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @return the number of nodes visited
     */
    public int regionSearch(int x, int y, int width, int height) {
        return root.regionSearch(x, y, width, height, 0, 0, SIZE);
    }


    /**
     * search for a point in the quadtree
     * 
     * @param p
     * @return the KVPair of the point if found
     */
    public KVPair<String, Point> search(Point p) {
        return root.search(p, 0, 0, SIZE - 1, SIZE - 1);
    }


    /**
     * remove a point from the quadtree
     * 
     * @param p
     *            the point to remove
     */
    public void remove(Point p) {
        root = root.remove(p, 0, 0, SIZE - 1, SIZE - 1);
    }

}
