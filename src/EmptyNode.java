/**
 * EmptyNode class that implements the QuadNode interface. The QuadTree points
 * to one instance of this class whenever an EmptyNode is needed to save space.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class EmptyNode implements QuadNode {

    private static EmptyNode flyweight = null;

    private EmptyNode() {

    }


    /**
     * Returns the instantiated EmptyNode or creates one if it has not already
     * been instantiated.
     * 
     * @return the EmptyNode
     */
    public static EmptyNode getInstance() {
        if (flyweight == null) {
            flyweight = new EmptyNode();
        }
        return flyweight;
    }


    /**
     * Creates a new LeafNode if the QuadTree lands on the EmptyNode for
     * insertion.
     */
    @Override
    public QuadNode insert(
        KVPair<String, Point> pair,
        int x,
        int y,
        int width,
        int height) {
        return new LeafNode(pair);
    }


    /**
     * Prints out the information regarding the EmptyNode.
     */
    @Override
    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ": Empty");
        return 1;

    }


    /**
     * Redundant method inherited from the QuadNode interface.
     * 
     * @param p
     * @param x
     * @param y
     * @param width
     * @param height
     * 
     * @return null
     */
    @Override
    public QuadNode remove(Point p, int x, int y, int width, int height) {
        return null;
    }


    /**
     * Returns 1 to indicate regionSearch hit the empty node.
     * 
     * @param searchX
     * @param searchY
     * @param searchWidth
     * @param searchHeight
     * @param currX
     * @param currY
     * @param size
     * 
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
        int size) {

        return 1;
    }


    /**
     * Redundant method inherited from the QuadNode interface.
     * 
     * @param p
     * @param x
     * @param y
     * @param width
     * @param height
     * 
     * @return null
     */
    @Override
    public KVPair<String, Point> search(
        Point p,
        int x,
        int y,
        int width,
        int height) {
        return null;
    }

}
