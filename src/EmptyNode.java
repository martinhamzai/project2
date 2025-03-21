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
    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ": Empty");
        return 1;

    }


    /**
     * Redundant method inherited from the QuadNode interface.
     */
    
    public QuadNode remove(Point p, int x, int y, int width, int height) {
        return null;
    }
    
    
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


    @Override
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height)
    {
        return null;
    }

<<<<<<< HEAD

=======
>>>>>>> 4de0c356c8a6e0a8bc588cf8363e341d6b7c0558
}
