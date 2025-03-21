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
    
    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ": Internal");
        
        int mid = size / 2;
        int count = 1;
        
        count += nw.dump(depth + 1, x, y, mid);
        count += ne.dump(depth + 1, x + mid, y, mid);
        count += sw.dump(depth + 1, x, y + mid, mid);
        count += se.dump(depth + 1, x + mid, y + mid, mid);
        
        return count;
    }


    @Override
    public KVPair<String, Point> remove(Point p, int x, int y, int width, int height)
    {
        int midX = (x + width) / 2;
        int midY = (y + height) / 2;
        
        if (p.getX() <= midX && p.getY() <= midY){
            return nw.remove(p, x, y, width/2, height/2);
        }
        else if (p.getX() > midX && p.getY() <= midY){
            return ne.remove(p, midX + 1, y, width/2, height/2);
        }
        else if (p.getX() <= midX && p.getY() > midY) {
            return sw.remove(p, x, midY + 1, width / 2, height / 2);
        }
        else
        {
            return se.remove(p, midX + 1, midY + 1, width / 2, height / 2);
        }
    }
    
}
