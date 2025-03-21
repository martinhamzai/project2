/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 18046
 *  @version Mar 21, 2025
 */
public class InternalNode<K extends Comparable<K>, E>  implements QuadNode{

    public static EmptyNode flyweight = null;
    
    QuadNode nw, ne, sw, se;
    int x, y, width, height;
    
    public InternalNode(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.nw = this.ne = this.sw = this.se = EmptyNode.getInstance();
    }
    
    
    @Override
    public boolean insert(KVPair<String, Point> pair, int xMin, int xMax, int yMin, int yMax)
    {
        // TODO Auto-generated method stub
        return false;
    }
    
}
