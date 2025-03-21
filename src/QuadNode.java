
public interface QuadNode {

    public QuadNode insert(KVPair<String, Point> pair, int x, int y, int width, int height);

    public int dump(int depth, int x, int y, int size);
    
    public KVPair<String, Point> remove(Point p, int x, int y, int width, int height);
    
}
