/**
 *  Write a one-sentence summary of your class here.
 *  Follow it with additional details about its purpose, what abstraction
 *  it represents, and how to use it.
 * 
 *  @author 18046
 *  @version Mar 21, 2025
 */
public class InternalNode implements QuadNode{

    public static EmptyNode flyweight = null;
    
    QuadNode nw, ne, sw, se;
    
    public InternalNode() {
        this.nw = this.ne = this.sw = this.se = EmptyNode.getInstance();
    }
    
    
    @Override
    public QuadNode insert(KVPair<String, Point> pair, int x, int y, int width, int height)
    {   
        Point p = pair.value();
        int midX = (x + width) / 2;
        int midY = (y + height) / 2;
        
        if (p.getX() <= midX && p.getY() <= midY){
            nw = nw.insert(pair, x, y, width/2, height/2);
        }
        else if (p.getX() > midX && p.getY() <= midY){
            ne = ne.insert(pair, midX + 1, y, width/2, height/2);
        }
        else if (p.getX() <= midX && p.getY() > midY) {
            sw = sw.insert(pair, x, midY + 1, width / 2, height / 2);
        }
        else
        {
            se = se.insert(pair, midX + 1, midY + 1, width / 2, height / 2);
        }
        
        return this;
    }
    
}
