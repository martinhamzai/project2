
public interface QuadNode {

    public QuadNode insert(
        KVPair<String, Point> pair,
        int x,
        int y,
        int width,
        int height);


    public int dump(int depth, int x, int y, int size);


    public QuadNode remove(Point p, int x, int y, int width, int height);


    public int regionSearch(
        int searchX,
        int searchY,
        int searchWidth,
        int searchHeight,
        int currX,
        int currY,
        int size);
    
    public KVPair<String, Point> search(Point p, int x, int y, int width, int height);
}

